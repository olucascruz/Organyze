package com.example.organyze.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.organyze.database.models.Materia

@Dao
interface MateriaDao {

    @Insert
    suspend fun insert(materia: Materia): Long

    @Query("SELECT COUNT(mid) FROM materia")
    suspend fun getTotalItems(): Long

    @Query("SELECT mid FROM materia WHERE nome = :materiaName")
    suspend fun getIdByName(materiaName: String): Long

    @Query("SELECT nome FROM materia WHERE mid = :materiaId")
    suspend fun getNameById(materiaId: Long): String?

    @Query("SELECT abreviacao FROM materia WHERE mid = :materiaId")
    suspend fun getAbreviacaoById(materiaId: Long): String?

    @Query("SELECT * FROM materia")
    suspend fun getAllMateria(): List<Materia>
}
