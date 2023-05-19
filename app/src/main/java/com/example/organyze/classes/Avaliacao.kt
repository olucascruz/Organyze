package com.example.organyze.classes

class Avaliacao constructor(
    private val nome: String,
    private var data: String,
    private val tema: String
) {
    private var descricao: String? = null
    private var nota: Number? = null
}