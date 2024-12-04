package com.hermosohermoso.danielmartin.packemon;

import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.model.Pokemon

/*
Esta clase debe tener las variables importantes del programa,
  en este caso el número de desserts vendidos
  el revenue total
  la imagen del dessert actual
  el precio del dessert actual
*/
data class PokeUiState(
//    val genSeleccionada: Int, // Generación seleccionada
//    val pokeSeleccionado: Pokemon // Pokémon seleccionado

//    De joseca
    val isLoading: Boolean = false,
    var pokemonList: List<PokemonCard> = emptyList(),
//    val pokeListShow: List<PokemonCard> = emptyList(),
    val pokemonNumberShow: Int = 0,
    val pokemonMostra: Boolean = false
    )

