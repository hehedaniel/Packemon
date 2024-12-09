package com.hermosohermoso.danielmartin.packemon.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hermosohermoso.danielmartin.packemon.data.api.PackemonApi
import com.hermosohermoso.danielmartin.packemon.data.bbdd.PackemonApplication
import com.hermosohermoso.danielmartin.packemon.preferences.PackemonPreferences
import kotlinx.coroutines.flow.asStateFlow

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
                    val randomNumber = (1..999).random()

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

            PackemonPreferences.cambiarNumPokemon(n)

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
