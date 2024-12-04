package com.hermosohermoso.danielmartin.packemon;

import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.bbdd.PokemonDDBB

/*
Esta clase debe tener las variables importantes del programa,
  en este caso el número de desserts vendidos
  el revenue total
  la imagen del dessert actual
  el precio del dessert actual
*/
data class PokeUiState(
    val isLoading: Boolean = false,
    var pokemonList: List<PokemonCard> = emptyList(),
    val pokemonNumberShow: Int = -1,
    val pokemonVistos: Boolean = false,
    val pokemonMostrarInfo: PokemonDDBB? = null,
    val num_pokemon_fila: Int = 1
)

