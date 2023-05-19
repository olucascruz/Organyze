package com.example.organyze.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "horario", foreignKeys = [ForeignKey(entity = Materia::class, parentColumns = ["mid"], childColumns = ["materiaId"], onDelete = ForeignKey.CASCADE)])
data class Horario(
    @ColumnInfo(name = "diaSemana") val diaSemana: String,
    @ColumnInfo(name = "horaInicio") val horaInicio: String,
    @ColumnInfo(name = "horaFim") val horaFim: String,
    @ColumnInfo(name = "materiaId") val materiaId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var hid: Int = 0
}
