package org.example.utils

import org.example.controller.GameController

class Menu {

    private val gameController = GameController()
    fun menu(){
        while(true){
            println("---- BIENVENIDO ----")
            println("1 - Añadir juego")
            println("2 - Listar juegos")
            println("3 - Buscar juego")
            println("4 - Buscar juego por genero")
            println("5 - Modificar juego")
            println("6 - eliminar juegos por genero")
            println("0 - Salir")
            print("Elija una opción -> ")

            val opcion = readln()

            when(opcion){
                "1" -> gameController.createGame()
                "2" -> gameController.listAllGames()
                "3" -> gameController.getGame()
                "4" -> gameController.getGameByGender()
                "5" -> gameController.updateGame()
                "6" -> gameController.deleteGame()
                "0" -> break
                else -> println("Opción no válida")
            }
        }
    }
}