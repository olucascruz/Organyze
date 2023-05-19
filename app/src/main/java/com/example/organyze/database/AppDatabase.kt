package com.example.organyze.database

import android.content.Context
import androidx.room.*
import com.example.organyze.database.daos.HorarioDao
import com.example.organyze.database.daos.MateriaDao
import com.example.organyze.database.models.Horario
import com.example.organyze.database.models.Materia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromHorarioList(value: List<Map<String, Map<String, String>>>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toHorarioList(value: String): List<Map<String, Map<String, String>>> {
        val listType = object : TypeToken<List<Map<String, Map<String, String>>>>() {}.type
        return Gson().fromJson(value, listType)
    }
}


@Database(entities = [Materia::class, Horario::class], version = 1)
@TypeConverters(Converters::class) // Adiciona o conversor personalizad
abstract class AppDatabase : RoomDatabase(){

    abstract fun materiaDao(): MateriaDao
    abstract fun horarioDao(): HorarioDao

    companion object {
        private const val DATABASE_NAME: String = "organyze-db"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
