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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hermosohermoso.danielmartin.packemon.api.PackemonApi
import com.hermosohermoso.danielmartin.packemon.api.PokemonApiService
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonApplication
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.bbdd.PokemonDDBB
import com.hermosohermoso.danielmartin.packemon.preferences.PackemonPreferences
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val PackemonPreferences: PackemonPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokeUiState())
    val uiState: StateFlow<PokeUiState> = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            PackemonPreferences.num_pokemon.collect{
                _uiState.value = _uiState.value.copy(
                    num_pokemon_fila = it
                )
            }
        }
    }

    // Función para obtener los pokemons desde la API
    fun fetchPokemons() {
        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)
            var listaAux: List<PokemonCard> = emptyList()

            while (listaAux.size < 6) {
                try {
                    val randomNumber = (1..251).random()

                    val query = "nationalPokedexNumbers:[$randomNumber TO $randomNumber]"
                    val response = PackemonApi.retrofitService.getPokemonByNationalPokedexNumber(query)

                    if (response.data.isNotEmpty()) {
                        listaAux = listaAux.plus(response.data[(1..response.data.size).random()])
                    } else {
                        Log.d("Packemon", "Falló la petición")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
//            Log.d("PokemonViewModel", "Petición terminada")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                pokemonList = listaAux
            )

        }
    }

    fun restarAlContador(){
        _uiState.value = _uiState.value.copy(pokemonNumberShow = _uiState.value.pokemonNumberShow - 1)
    }

    fun sumarAlContador(){
        _uiState.value = _uiState.value.copy(pokemonNumberShow = _uiState.value.pokemonNumberShow + 1)
    }

    fun resetearContador(){
        _uiState.value = _uiState.value.copy(pokemonNumberShow = -1)
    }

    fun guardarPokemonMostrar(pokemon: PokemonDDBB) {
        _uiState.value = _uiState.value.copy(pokemonMostrarInfo = pokemon)
    }

    fun obtenerPokemonMostrar(): PokemonDDBB? {
        return _uiState.value.pokemonMostrarInfo
    }

    fun pokemonVistos(){
        _uiState.value = _uiState.value.copy(pokemonVistos = true)
    }

    fun pokemonPorVer(){
        _uiState.value = _uiState.value.copy(pokemonVistos = false)
    }

//    Para las preferences

    fun cambiarNumPokemon(){
        val numPokemon = _uiState.value.num_pokemon_fila
        viewModelScope.launch {
            val n = when(numPokemon){
                1 -> 2
                2 -> 3
                3 -> 4
                else -> 1
            }
            _uiState.value = _uiState.value.copy(
                num_pokemon_fila = n
            )
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PackemonApplication)
                PokemonViewModel(application.gridNumber)
            }
        }
    }

}
