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

    fun obtenerSiguientePokemon(): PokemonCard? {
        val n = _uiState.value.pokemonNumberShow
        if (n < _uiState.value.pokemonList.size) {
            return _uiState.value.pokemonList[n]
        }
        return null
    }

}













//                // Hacemos la llamada a la API para obtener los Pokémon
//                val response = PackemonApi.retrofitService.getPokemonList()
//
//                // Imprime toda la respuesta en consola
//                Log.d("PokemonViewModel", "Response: $response")
//
//                if (response.data.isEmpty()) {
//                    Log.d("PokemonViewModel", "No se han obtenido pokemons")
//                } else {
//                    _uiState.value = _uiState.value.copy(
//                        isLoading = false,
//                        pokemonList = response.data
//                    )
//                }










//sealed interface PokemonUiState {
//    data class Success(val pokemons: List<PokemonCard>) : PokemonUiState
//    object Error : PokemonUiState
//    object Loading : PokemonUiState
//}
//
//class PokemonViewModel : ViewModel() {
//    private val _uiState = MutableStateFlow(
//        PokeUiState(
//            genSeleccionada = 1,
////            pokeSeleccionado = DatasourceGen1.pokemonGen1List[0]
//        )
//    )
//
////    val uiState: StateFlow<PokeUiState> = _uiState
//    var uiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
//        private set
//
//    private fun fetchFirstGenerationPokemon() {
//        viewModelScope.launch {
//            val apiService = PokemonApiFactory.makePokemonService()
//            val apiKey = "TU_CLAVE_API_AQUÍ" // Reemplaza con tu clave API
//
//            try {
//                uiState = PokemonUiState.Loading
//                val response = apiService.getFirstGenerationPokemon(apiKey)
//                uiState = PokemonUiState.Success(response.data)
//            } catch (e: IOException) {
//                uiState = PokemonUiState.Error
//            }
//        }
//    }

//    fun seleccionarGeneracion(generacion: Int) {
//        _uiState.update {
//            val listaPokemon = when (generacion) {
//                1 -> DatasourceGen1.pokemonGen1List
//                2 -> DatasourceGen2.pokemonGen2List
//                3 -> DatasourceGen3.pokemonGen3List
//                4 -> DatasourceGen4.pokemonGen4List
//                else -> emptyList()
//            }
//            it.copy(
//                genSeleccionada = generacion,
//                pokeSeleccionado = listaPokemon.get(0)
//            )
//        }
//    }

//    fun seleccionarPokemon(pokemon: Pokemon) {
//        _uiState.update {
//            it.copy(pokeSeleccionado = pokemon)
//        }
//    }
//}


//Claro, pero yo lo que debo hacer es hacer la peticion con por jemplo el numero 7, la hago, me devuelve una lista de pokemon y de esta lista obtengo el 0, el primeor, y este pokecard que obtengo me lo guardo en la lista final que seria la del uistate, y esto lo hago 5 veces para obtener 5 pokes diferentes