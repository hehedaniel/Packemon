package com.hermosohermoso.danielmartin.packemon.data.api

import com.hermosohermoso.danielmartin.packemon.model.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.pokemontcg.io/v2/")
    .build()

interface PokemonApiService {
    @GET("cards")
    suspend fun getPokemonByNationalPokedexNumber(
        @Query("q") query: String
    ): PokemonResponse
}


object PackemonApi{
    val retrofitService: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }
}
