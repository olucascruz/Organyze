package com.example.organyze.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Query
import com.example.organyze.database.models.Materia

@Dao
interface MateriaDao {

    @Insert
    fun insert(materia: Materia): Long

    @Query("SELECT COUNT(mid) FROM materia")
    fun getTotalItems(): Long

    @Query("SELECT mid FROM materia WHERE nome = :materiaName")
    fun getIdByName(materiaName: String): Long

    @Query("SELECT nome FROM materia WHERE mid = :materiaId")
    fun getNameById(materiaId: Long): String?

    @Query("SELECT abreviacao FROM materia WHERE mid = :materiaId")
    fun getAbreviacaoById(materiaId: Long): String?

    @Query("SELECT * FROM materia")
    fun getAllMateria(): List<Materia>

    @Delete
    fun delete(materia: Materia): Void
}
