package org.example.controller

import org.example.model.Game
import org.example.service.GameService
import java.util.*

class GameController {
    private val gameService = GameService()

    fun createGame(){
        println("Ingrese los datos del nuevo juego:")
        print("Título: ")
        val titulo = readln()
        print("Género: ")
        val genero = readln()
        print("Precio: ")
        val precio = readln().toDoubleOrNull()
        print("Fecha de lanzamiento (yyyy-MM-dd): ")
        val fechaSalida = readln()

        if (titulo.isBlank() || genero.isBlank() || precio == null || fechaSalida.isBlank()) {
            println("Datos inválidos. Por favor, complete todos los campos correctamente.")
            return
        }

        val fechaLanz = try {
            Date.from(java.time.LocalDate.parse(fechaSalida).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
        } catch (e: Exception) {
            println("Fecha inválida. Asegúrese de usar el formato yyyy-MM-dd.")
            return
        }

        val game = Game(titulo, genero, precio, fechaLanz)
        if (gameService.createGame(game)) {
            println("Juego creado exitosamente.")
        } else {
            println("Error al crear el juego.")
        }
    }

    fun listAllGames():List<Game>{
        return gameService.listAllGames()
    }

    fun updateGame(){
        println("Ingrese el título del juego que desea actualizar:")
        val titulo = readln()
        print("Nuevo género: ")
        val genero = readln()
        print("Nuevo precio: ")
        val precio = readln().toDoubleOrNull()
        print("Nueva fecha de lanzamiento (yyyy-MM-dd): ")
        val fechaSalida = readln()

        if (titulo.isBlank() || genero.isBlank() || precio == null || fechaSalida.isBlank()) {
            println("Datos inválidos. Por favor, complete todos los campos correctamente.")
            return
        }

        val fechaLanz = try {
            Date.from(java.time.LocalDate.parse(fechaSalida).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant())
        } catch (e: Exception) {
            println("Fecha inválida. Asegúrese de usar el formato yyyy-MM-dd.")
            return
        }

        val game = Game(titulo, genero, precio, fechaLanz)
        val updatedGame = gameService.updateGame(game)

        if (updatedGame != null) {
            println("Juego actualizado correctamente: $updatedGame")
        } else {
            println("No se pudo actualizar el juego. Verifique el título.")
        }
    }

    fun getGame(){
        println("Ingrese el título del juego que desea buscar:")
        val titulo = readln()

        if (titulo.isBlank()) {
            println("El título no puede estar vacío.")
            return
        }

        val game = gameService.getGame(titulo)

        if (game != null) {
            println("Juego encontrado: $game")
        } else {
            println("No se encontró ningún juego con el título: $titulo")
        }
    }

    fun getGameByGender(){
        println("Ingrese el género de los juegos que desea buscar:")
        val gender = readln()

        if (gender.isBlank()) {
            println("El género no puede estar vacío.")
            return
        }

        val games = gameService.getGameByGender(gender)

        if (games.isEmpty()) {
            println("No se encontraron juegos con el género: $gender")
        } else {
            println("Juegos encontrados:")
            games.forEach { println(it) }
        }

    }

    fun deleteGame(){
        do {
            println("Ingrese el género de los juegos a eliminar:")
            val gender = readln()

            if (gender.isBlank()) {
                println("El género no puede estar vacío.")
            } else {
                val success = gameService.deleteGameByGender(gender)
                if (success) {
                    println("Juegos con género '$gender' eliminados correctamente.")
                } else {
                    println("No se encontraron juegos con el género: $gender.")
                }
            }
        } while (gender.isBlank())
    }

}