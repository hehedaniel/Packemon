package com.hermosohermoso.danielmartin.packemon.old

import com.hermosohermoso.danielmartin.packemon.api.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PokemonApiService {
    @GET("cards")
    suspend fun getFirstGenerationPokemon(
        @Header("X-Api-Key") apiKey: String, // Clave de API para autenticación
        @Query("q") query: String = "nationalPokedexNumbers:[1 TO 151]"
    ): Response<PokemonResponse>
}
