package com.hermosohermoso.danielmartin.packemon.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.hermosohermoso.danielmartin.packemon.api.PackemonApi
import com.hermosohermoso.danielmartin.packemon.api.PokemonApiService
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PokeUiState())
    val uiState: StateFlow<PokeUiState> = _uiState.asStateFlow()

    // Función para obtener los pokemons desde la API
    fun fetchPokemons() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)
            var listaAux: List<PokemonCard> = emptyList()

            while (listaAux.size < 6) {
                try {
                    val randomNumber = (1..151).random()

                    val query = "nationalPokedexNumbers:[$randomNumber TO $randomNumber]"
                    val response = PackemonApi.retrofitService.getPokemonByNationalPokedexNumber(query)
                    Log.d("PokemonViewModel", "${response.data.size}")
                    if (response.data.isNotEmpty()) {
                        val pokemon = response.data[0]
                        listaAux = listaAux.plus(pokemon)
                    } else {
                        Log.d("PokemonViewModel", "No se encontró el Pokémon")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            Log.d("PokemonViewModel", "Petición terminada")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                pokemonList = listaAux
            )

        }
    }

    fun sumarAlContador(){
        _uiState.value = _uiState.value.copy(pokemonNumberShow = _uiState.value.pokemonNumberShow + 1)
    }

    fun guardarPokemonMostrar(pokemon: PokemonCard) {
        _uiState.value = _uiState.value.copy(pokemonMostrarInfo = pokemon)
    }

    fun obtenerPokemonMostrar(): PokemonCard? {
        return _uiState.value.pokemonMostrarInfo
    }


}
