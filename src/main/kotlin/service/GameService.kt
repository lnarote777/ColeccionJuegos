package org.example.service

import com.mongodb.client.model.Filters
import org.example.model.Game
import org.example.utils.ConnectionDB

class GameService {

    private val connection = ConnectionDB.getDatabase("lnarote")
    private val collection = connection.getCollection("colljuegos")

    fun createGame(){}

    fun updateGame(game: Game): Game{

    }

    fun getGameByGender(gender: String): MutableList<Game> {
        val gameList = mutableListOf<Game>()
        val games = Filters.eq("genero", gender)
        val busc = collection.find(games)

        busc.forEach { game ->
            gameList.add(Game(game.getString("titulo"), game.getString("genero"), game.getDouble("precio"), game.getDate("fecha_lanz")))
        }

        return gameList
    }

    fun deleteGameByGender(gender: String) {
        val filter = Filters.eq("genero", gender)
        val busc = collection.find(filter)

        if (busc.toList().isEmpty()){
            println("No se encontraron juegos con el género: $gender")
        }else{
            collection.deleteMany(filter)

        }

    }
}