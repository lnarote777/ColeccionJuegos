package org.example.utils

import org.example.controller.GameController

class Menu {

    private val gameController = GameController()
    fun menu(){
        while(true){
            println("---- BIENVENIDO ----")
            println("1 - Añadir juego")
            println("2 - Buscar juego")
            println("3 - Buscar juego por genero")
            println("4 - Modificar juego")
            println("5 - eliminar juegos por genero")
            println("0 - Salir")
            print("Elija una opción -> ")

            val opcion = readln()

            when(opcion){
                "1" -> gameController.createGame()
                "2" -> gameController.getGame()
                "3" -> gameController.getGameByGender()
                "4" -> gameController.updateGame()
                "5" -> gameController.deleteGame()
                "0" -> break
                else -> println("Opción no válida")
            }
        }
    }
}