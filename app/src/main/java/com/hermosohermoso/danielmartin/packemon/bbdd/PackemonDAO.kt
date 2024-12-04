package com.hermosohermoso.danielmartin.packemon.bbdd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import kotlinx.coroutines.flow.Flow

@Dao
interface PackemonDAO{
   @Query("SELECT * FROM Pokemon")
   fun recogerPokemons(): Flow<List<PokemonDDBB>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun guardarPokemon(pokemonCard: PokemonDDBB)
}