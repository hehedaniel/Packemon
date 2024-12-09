package com.hermosohermoso.danielmartin.packemon.data.bbdd

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermosohermoso.danielmartin.packemon.model.PokemonDDBB
import kotlinx.coroutines.flow.Flow

@Dao
interface PackemonDAO{
   @Query("SELECT * FROM Pokemon")
   fun recogerPokemons(): Flow<List<PokemonDDBB>>

   @Query("SELECT * FROM Pokemon WHERE fav = 1")
   fun recogerPokemonsFav(): Flow<List<PokemonDDBB>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun guardarPokemon(pokemonCard: PokemonDDBB)

   @Query("UPDATE Pokemon SET fav = :isFav WHERE pokeId = :pokeId")
   suspend fun actualizarFavorito(pokeId: String, isFav: Boolean)

   @Delete
   suspend fun eliminarPokemon(pokemonCard: PokemonDDBB)
}