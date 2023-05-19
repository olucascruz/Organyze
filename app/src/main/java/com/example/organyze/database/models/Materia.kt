package com.example.organyze.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "materia")
data class Materia(

    @ColumnInfo(name = "nome") val nome: String,
    @ColumnInfo(name = "abreviacao") val abreviacao: String

    ){
    @PrimaryKey(autoGenerate = true)
    var mid : Int = 0
}
