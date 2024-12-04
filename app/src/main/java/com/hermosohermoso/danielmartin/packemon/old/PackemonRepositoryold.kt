package com.hermosohermoso.danielmartin.packemon.old

import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun fetchFirstGenerationPokemon(apiKey: String): List<PokemonCard>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = RetrofitInstance.api.getFirstGenerationPokemon(apiKey)
            if (response.isSuccessful) {
                response.body()?.data
            } else {
                println("Error: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            println("Exception: ${e.message}")
            null
        }
    }
}
