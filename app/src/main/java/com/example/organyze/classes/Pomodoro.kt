package com.example.organyze.classes

import com.example.organyze.database.models.Materia

class Pomodoro constructor(
    private val materia: Materia,
    private val tema: String,
    private val tempo: Number
){
}