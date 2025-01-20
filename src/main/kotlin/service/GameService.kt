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
            val exists = collection.find(Filters.eq("titulo", game.titulo)).firstOrNull()
            if (exists != null) {
                println("Error: Ya existe un juego con el título '${game.titulo}'.")
                return false
            }

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

    fun getGame(titulo: String): Game?{
        return try {
            val filter = Filters.eq("titulo", titulo)
            val gameDoc = collection.find(filter).firstOrNull()

            if (gameDoc != null) {
                val title = gameDoc.getString("titulo") ?: "Sin título"
                val genero = gameDoc.getString("genero") ?: "Sin género"
                val precio = gameDoc.getDouble("precio") ?: 0.0
                val fechaLanz = gameDoc.getDate("fecha_lanz") ?: Date()

                Game(title, genero, precio, fechaLanz)
            } else {
                println("No se encontró ningún juego con el título: $titulo")
                null
            }
        } catch (e: Exception) {
            println("Error al buscar el juego: ${e.message}")
            null
        }
    }

    fun listAllGames(): List<Game> {
        val games = mutableListOf<Game>()
        collection.find().forEach { doc ->
            val titulo = doc.getString("titulo") ?: "Sin título"
            val genero = doc.getString("genero") ?: "Sin género"
            val precio = doc.getDouble("precio") ?: 0.0
            val fechaLanz = doc.getDate("fecha_lanz") ?: Date()
            games.add(Game(titulo, genero, precio, fechaLanz))
        }
        return games
    }

    fun getGameByGender(gender: String): MutableList<Game> {
        val gameList = mutableListOf<Game>()
        try {
            val filter = Filters.eq("genero", gender)
            val busc = collection.find(filter).sort(Document("titulo", 1))


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