package org.example.controller

import org.example.service.GameService

class GameController {
    private val gameService = GameService()

    fun createGame(){

    }

    fun updateGame(){

    }

    fun getGame(){

    }

    fun getGameByGender(){


    }


    fun deleteGame(){
        do{
            println("Genero a eliminar ->")
            val gender = readln()

            if (gender.isBlank()){
                print("Argumentos inválidos")
            }else{
                gameService.deleteGameByGender(gender)
            }
        }while(gender.isBlank())

    }


}