package org.example.project.data

import android.content.ContentValues
import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.domain.Exercicio
import org.example.project.domain.Treino
import org.example.project.domain.TreinoId

class TreinoRepository(context: Context) {

    private val dbHelper = TreinoDbHelper(context)
    private val _treinos = MutableStateFlow<List<Treino>>(emptyList())
    val treinos: StateFlow<List<Treino>> = _treinos.asStateFlow()

    init {
        carregarTreinos()
    }

    private fun carregarTreinos() {
        val db = dbHelper.readableDatabase
        val list = mutableListOf<Treino>()

        val cursorT = db.query(TreinoDbHelper.TABLE_TREINO, null, null, null, null, null, null)
        try {
            if (cursorT.moveToFirst()) {
                do {
                    val id = cursorT.getLong(cursorT.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_TREINO_ID))
                    val nome = cursorT.getString(cursorT.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_TREINO_NOME))
                    val descricao = cursorT.getString(cursorT.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_TREINO_DESCRICAO)) ?: ""

                    // Buscar exercícios deste treino
                    val exercises = mutableListOf<Exercicio>()
                    val cursorE = db.query(
                        TreinoDbHelper.TABLE_EXERCICIO,
                        null,
                        "${TreinoDbHelper.COLUMN_EXERCICIO_TREINO_ID} = ?",
                        arrayOf(id.toString()),
                        null, null, null
                    )
                    try {
                        if (cursorE.moveToFirst()) {
                            do {
                                val exId = cursorE.getLong(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_ID))
                                val exNome = cursorE.getString(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_NOME))
                                val series = cursorE.getInt(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_SERIES))
                                val reps = cursorE.getInt(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_REPETICOES))
                                val carga = cursorE.getDouble(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_CARGA))
                                val concluidoVal = cursorE.getInt(cursorE.getColumnIndexOrThrow(TreinoDbHelper.COLUMN_EXERCICIO_CONCLUIDO))
                                val concluido = concluidoVal == 1

                                exercises.add(Exercicio(exId, exNome, series, reps, carga, concluido))
                            } while (cursorE.moveToNext())
                        }
                    } finally {
                        cursorE.close()
                    }

                    list.add(Treino(TreinoId(id), nome, descricao, exercises))
                } while (cursorT.moveToNext())
            }
        } finally {
            cursorT.close()
        }

        _treinos.value = list
    }

    fun adicionarTreino(nome: String, descricao: String): TreinoId {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_TREINO_NOME, nome)
            put(TreinoDbHelper.COLUMN_TREINO_DESCRICAO, descricao)
        }
        val id = db.insert(TreinoDbHelper.TABLE_TREINO, null, values)
        carregarTreinos()
        return TreinoId(id)
    }

    fun removerTreino(id: TreinoId) {
        val db = dbHelper.writableDatabase
        db.delete(TreinoDbHelper.TABLE_TREINO, "${TreinoDbHelper.COLUMN_TREINO_ID} = ?", arrayOf(id.value.toString()))
        carregarTreinos()
    }

    fun adicionarExercicio(
        treinoId: TreinoId, nome: String, series: Int, repeticoes: Int, cargaKg: Double
    ) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_EXERCICIO_TREINO_ID, treinoId.value)
            put(TreinoDbHelper.COLUMN_EXERCICIO_NOME, nome)
            put(TreinoDbHelper.COLUMN_EXERCICIO_SERIES, series)
            put(TreinoDbHelper.COLUMN_EXERCICIO_REPETICOES, repeticoes)
            put(TreinoDbHelper.COLUMN_EXERCICIO_CARGA, cargaKg)
            put(TreinoDbHelper.COLUMN_EXERCICIO_CONCLUIDO, 0)
        }
        db.insert(TreinoDbHelper.TABLE_EXERCICIO, null, values)
        carregarTreinos()
    }

    fun alternarConclusao(treinoId: TreinoId, exercicioId: Long) {
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            TreinoDbHelper.TABLE_EXERCICIO,
            arrayOf(TreinoDbHelper.COLUMN_EXERCICIO_CONCLUIDO),
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString()),
            null, null, null
        )
        var concluidoVal = 0
        try {
            if (cursor.moveToFirst()) {
                concluidoVal = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }

        val newConcluidoVal = if (concluidoVal == 1) 0 else 1
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_EXERCICIO_CONCLUIDO, newConcluidoVal)
        }
        db.update(
            TreinoDbHelper.TABLE_EXERCICIO,
            values,
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString())
        )
        carregarTreinos()
    }

    fun removerExercicio(treinoId: TreinoId, exercicioId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(
            TreinoDbHelper.TABLE_EXERCICIO,
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString())
        )
        carregarTreinos()
    }

    fun alterarCarga(treinoId: TreinoId, exercicioId: Long, delta: Double) {
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            TreinoDbHelper.TABLE_EXERCICIO,
            arrayOf(TreinoDbHelper.COLUMN_EXERCICIO_CARGA),
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString()),
            null, null, null
        )
        var carga = 0.0
        try {
            if (cursor.moveToFirst()) {
                carga = cursor.getDouble(0)
            }
        } finally {
            cursor.close()
        }
        val novaCarga = (carga + delta).coerceAtLeast(0.0)
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_EXERCICIO_CARGA, novaCarga)
        }
        db.update(
            TreinoDbHelper.TABLE_EXERCICIO,
            values,
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString())
        )
        carregarTreinos()
    }

    fun alterarRepeticoes(treinoId: TreinoId, exercicioId: Long, delta: Int) {
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            TreinoDbHelper.TABLE_EXERCICIO,
            arrayOf(TreinoDbHelper.COLUMN_EXERCICIO_REPETICOES),
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString()),
            null, null, null
        )
        var reps = 1
        try {
            if (cursor.moveToFirst()) {
                reps = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        val novasReps = (reps + delta).coerceAtLeast(1)
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_EXERCICIO_REPETICOES, novasReps)
        }
        db.update(
            TreinoDbHelper.TABLE_EXERCICIO,
            values,
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString())
        )
        carregarTreinos()
    }

    fun alterarSeries(treinoId: TreinoId, exercicioId: Long, delta: Int) {
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            TreinoDbHelper.TABLE_EXERCICIO,
            arrayOf(TreinoDbHelper.COLUMN_EXERCICIO_SERIES),
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString()),
            null, null, null
        )
        var series = 1
        try {
            if (cursor.moveToFirst()) {
                series = cursor.getInt(0)
            }
        } finally {
            cursor.close()
        }
        val novasSeries = (series + delta).coerceAtLeast(1)
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_EXERCICIO_SERIES, novasSeries)
        }
        db.update(
            TreinoDbHelper.TABLE_EXERCICIO,
            values,
            "${TreinoDbHelper.COLUMN_EXERCICIO_ID} = ?",
            arrayOf(exercicioId.toString())
        )
        carregarTreinos()
    }

    fun redefinirTemplates() {
        val db = dbHelper.writableDatabase
        db.delete(TreinoDbHelper.TABLE_EXERCICIO, null, null)
        db.delete(TreinoDbHelper.TABLE_TREINO, null, null)
        dbHelper.seedData(db)
        carregarTreinos()
    }

    fun cadastrarUsuario(usuario: String, senha: String): Boolean {
        val db = dbHelper.writableDatabase

        // Verificar se usuário já existe
        val cursor = db.query(
            TreinoDbHelper.TABLE_USUARIO,
            arrayOf(TreinoDbHelper.COLUMN_USUARIO_ID),
            "${TreinoDbHelper.COLUMN_USUARIO_NOME} = ?",
            arrayOf(usuario),
            null, null, null
        )
        val existe = cursor.use { it.count > 0 }
        if (existe) return false

        // Inserir novo usuário
        val values = ContentValues().apply {
            put(TreinoDbHelper.COLUMN_USUARIO_NOME, usuario)
            put(TreinoDbHelper.COLUMN_USUARIO_SENHA, senha)
        }
        val result = db.insert(TreinoDbHelper.TABLE_USUARIO, null, values)
        return result != -1L
    }

    fun validarLogin(usuario: String, senha: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TreinoDbHelper.TABLE_USUARIO,
            arrayOf(TreinoDbHelper.COLUMN_USUARIO_ID),
            "${TreinoDbHelper.COLUMN_USUARIO_NOME} = ? AND ${TreinoDbHelper.COLUMN_USUARIO_SENHA} = ?",
            arrayOf(usuario, senha),
            null, null, null
        )
        return cursor.use { it.count > 0 }
    }
}
