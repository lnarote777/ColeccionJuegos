package org.example.model

import java.util.Date

data class Game(
    val titulo: String,
    val genero: String,
    val precio: Double,
    val fechaLanz: Date
){
    init {
        if (titulo.isBlank() || genero.isBlank() || precio < 0 ) {
            throw IllegalArgumentException("Invalid arguments")
        }
    }
}
