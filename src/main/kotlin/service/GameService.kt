package org.example.service

import com.mongodb.client.model.Filters
import org.bson.Document
import org.example.model.Game
import org.example.utils.ConnectionDB
import java.util.*

class GameService {

    private val connection = ConnectionDB.getDatabase("lnarote")
    private val collection = connection.getCollection("colljuegos")

    fun createGame(game: Game): Boolean{
        return try {
            val newGame = Document().apply {
                append("titulo", game.titulo)
                append("genero", game.genero)
                append("precio", game.precio)
                append("fecha_lanz", game.fechaLanz)
            }
            collection.insertOne(newGame)
            println("Juego creado correctamente: ${game.titulo}")
            true
        } catch (e: Exception) {
            println("Error al crear el juego: ${e.message}")
            false
        }
    }

    fun updateGame(game: Game): Game?{
        val filter = Filters.eq("titulo", game.titulo)
        val busc = collection.find(filter).firstOrNull()

        return if (busc == null){
            println("No se encontro ningun juego con titulo: '${game.titulo}'")
            null
        }else{
            val update = Document("\$set", Document().apply {
                append("titulo", game.titulo)
                append("genero", game.genero)
                append("precio", game.precio)
                append("fecha_lanz", game.fechaLanz)
            })

            collection.updateOne(filter, update)
            println("Juego actualizado correctamente")
            game
        }
    }

    fun getGameByGender(gender: String): MutableList<Game> {
        val gameList = mutableListOf<Game>()
        try {
            val games = Filters.eq("genero", gender)
            val busc = collection.find(games)

            busc.forEach { game ->
                val titulo = game.getString("titulo") ?: "Sin título"
                val genero = game.getString("genero") ?: "Sin género"
                val precio = game.getDouble("precio") ?: 0.0
                val fechaLanz = game.getDate("fecha_lanz") ?: Date()

                gameList.add(Game(titulo, genero, precio, fechaLanz))
            }

        }catch (e: Exception){
            println("Error al obtener juegos por género: ${e.message}")
        }

        return gameList
    }

    fun deleteGameByGender(gender: String): Boolean {
        return try {
            val filter = Filters.eq("genero", gender)
            val result = collection.deleteMany(filter)

            if (result.deletedCount > 0) {
                println("Juegos con género '$gender' eliminados correctamente: ${result.deletedCount}")
                true
            } else {
                println("No se encontraron juegos con el género: $gender")
                false
            }
        } catch (e: Exception) {
            println("Error al eliminar juegos: ${e.message}")
            false
        }
    }
}