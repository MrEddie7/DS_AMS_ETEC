package org.example.model

enum class TipoExercicio(val descricao: String) {
    PEITO("Peito"),
    COSTAS("Costas"),
    OMBROS("Ombros"),
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    PERNAS("Pernas"),
    ABDOMEN("Abdômen"),
    GLUTEOS("Glúteos"),
    CARDIO("Cardio"),
    OUTRO("Outro");

    companion object {
        fun fromOrdinal(ordinal: Int): TipoExercicio? =
            entries.getOrNull(ordinal)
    }
}