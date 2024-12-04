package com.hermosohermoso.danielmartin.packemon.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://api.pokemontcg.io/v2/")
    .build()

//interface PokemonApiService {
//    @GET("cards")
//    suspend fun getPokemonList(
//        @Query("set") set: String = "base", // Filtro por set (ajústalo según la API)
//        @Query("page") page: String = "1",
//        @Query("pageSize") pageSize: String = "151"
//    ): PokemonResponse
//}

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

//
//object PokemonApiFactory {
//    fun makeRetrofitService(): PokemonApiService {
//        val json = Json {
//            ignoreUnknownKeys = true
//        }
//
//        return Retrofit.Builder()
//            .baseUrl("https://api.pokemontcg.io/v2/")
//            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
//            .build()
//            .create(PokemonApiService::class.java)
//    }
//}