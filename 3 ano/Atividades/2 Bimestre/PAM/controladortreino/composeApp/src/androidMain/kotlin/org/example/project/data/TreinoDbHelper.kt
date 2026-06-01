package org.example.project.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TreinoDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "treinos.db"
        const val DATABASE_VERSION = 1

        // Tabela Treino
        const val TABLE_TREINO = "treino"
        const val COLUMN_TREINO_ID = "id"
        const val COLUMN_TREINO_NOME = "nome"
        const val COLUMN_TREINO_DESCRICAO = "descricao"

        // Tabela Exercicio
        const val TABLE_EXERCICIO = "exercicio"
        const val COLUMN_EXERCICIO_ID = "id"
        const val COLUMN_EXERCICIO_TREINO_ID = "treino_id"
        const val COLUMN_EXERCICIO_NOME = "nome"
        const val COLUMN_EXERCICIO_SERIES = "series"
        const val COLUMN_EXERCICIO_REPETICOES = "repeticoes"
        const val COLUMN_EXERCICIO_CARGA = "carga"
        const val COLUMN_EXERCICIO_CONCLUIDO = "concluido"

        // Tabela Usuario
        const val TABLE_USUARIO = "usuario"
        const val COLUMN_USUARIO_ID = "id"
        const val COLUMN_USUARIO_NOME = "usuario"
        const val COLUMN_USUARIO_SENHA = "senha"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableTreino = """
            CREATE TABLE $TABLE_TREINO (
                $COLUMN_TREINO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TREINO_NOME TEXT NOT NULL,
                $COLUMN_TREINO_DESCRICAO TEXT
            )
        """.trimIndent()

        val createTableExercicio = """
            CREATE TABLE $TABLE_EXERCICIO (
                $COLUMN_EXERCICIO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_EXERCICIO_TREINO_ID INTEGER NOT NULL,
                $COLUMN_EXERCICIO_NOME TEXT NOT NULL,
                $COLUMN_EXERCICIO_SERIES INTEGER NOT NULL,
                $COLUMN_EXERCICIO_REPETICOES INTEGER NOT NULL,
                $COLUMN_EXERCICIO_CARGA REAL NOT NULL,
                $COLUMN_EXERCICIO_CONCLUIDO INTEGER NOT NULL DEFAULT 0,
                FOREIGN KEY ($COLUMN_EXERCICIO_TREINO_ID) REFERENCES $TABLE_TREINO($COLUMN_TREINO_ID) ON DELETE CASCADE
            )
        """.trimIndent()

        val createTableUsuario = """
            CREATE TABLE $TABLE_USUARIO (
                $COLUMN_USUARIO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USUARIO_NOME TEXT UNIQUE NOT NULL,
                $COLUMN_USUARIO_SENHA TEXT NOT NULL
            )
        """.trimIndent()

        db.execSQL(createTableTreino)
        db.execSQL(createTableExercicio)
        db.execSQL(createTableUsuario)

        // Seed data
        seedData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${TABLE_EXERCICIO}")
        db.execSQL("DROP TABLE IF EXISTS ${TABLE_TREINO}")
        db.execSQL("DROP TABLE IF EXISTS ${TABLE_USUARIO}")
        onCreate(db)
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        // Habilitar suporte a chaves estrangeiras para deleção em cascata funcionar
        db.setForeignKeyConstraintsEnabled(true)
    }

    fun seedData(db: SQLiteDatabase) {
        // Seed default user admin/admin
        val defaultUserValues = ContentValues().apply {
            put(COLUMN_USUARIO_NOME, "admin")
            put(COLUMN_USUARIO_SENHA, "admin")
        }
        db.insert(TABLE_USUARIO, null, defaultUserValues)

        // Treino A
        val tAId = inserirTreinoSeed(db, "Treino A - Peito, Tríceps & Ombro", "Foco em empurrar (Hipertrofia)")
        inserirExercicioSeed(db, tAId, "Supino Reto", 4, 10, 40.0)
        inserirExercicioSeed(db, tAId, "Crucifixo Inclinado", 3, 12, 14.0)
        inserirExercicioSeed(db, tAId, "Tríceps Corda", 4, 12, 25.0)
        inserirExercicioSeed(db, tAId, "Elevação Lateral", 4, 12, 8.0)

        // Treino B
        val tBId = inserirTreinoSeed(db, "Treino B - Costas & Bíceps", "Foco em puxar (Densidade)")
        inserirExercicioSeed(db, tBId, "Puxada Alta na Polia", 4, 10, 50.0)
        inserirExercicioSeed(db, tBId, "Remada Curvada", 4, 10, 35.0)
        inserirExercicioSeed(db, tBId, "Rosca Direta HBL", 3, 12, 12.0)
        inserirExercicioSeed(db, tBId, "Rosca Martelo Halteres", 3, 12, 10.0)

        // Treino C
        val tCId = inserirTreinoSeed(db, "Treino C - Pernas & Abdômen", "Foco em membros inferiores e core")
        inserirExercicioSeed(db, tCId, "Agachamento Livre", 4, 10, 60.0)
        inserirExercicioSeed(db, tCId, "Leg Press 45º", 4, 10, 120.0)
        inserirExercicioSeed(db, tCId, "Cadeira Extensora", 4, 12, 45.0)
        inserirExercicioSeed(db, tCId, "Abdominal Supra", 4, 20, 0.0)
    }

    private fun inserirTreinoSeed(db: SQLiteDatabase, nome: String, descricao: String): Long {
        val values = ContentValues().apply {
            put(COLUMN_TREINO_NOME, nome)
            put(COLUMN_TREINO_DESCRICAO, descricao)
        }
        return db.insert(TABLE_TREINO, null, values)
    }

    private fun inserirExercicioSeed(db: SQLiteDatabase, treinoId: Long, nome: String, series: Int, reps: Int, carga: Double) {
        val values = ContentValues().apply {
            put(COLUMN_EXERCICIO_TREINO_ID, treinoId)
            put(COLUMN_EXERCICIO_NOME, nome)
            put(COLUMN_EXERCICIO_SERIES, series)
            put(COLUMN_EXERCICIO_REPETICOES, reps)
            put(COLUMN_EXERCICIO_CARGA, carga)
            put(COLUMN_EXERCICIO_CONCLUIDO, 0)
        }
        db.insert(TABLE_EXERCICIO, null, values)
    }
}
