package com.example.organyze.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.organyze.database.models.Horario

@Dao
interface HorarioDao {
    @Insert
    fun insert(horario: Horario): Long

    @Query("SELECT COUNT(hid) FROM horario")
    fun getTotalItems() : Long

    @Query("SELECT * from horario WHERE diaSemana = :diaBuscado")
    fun  getHorarioByDay(diaBuscado: String): List<Horario>
}