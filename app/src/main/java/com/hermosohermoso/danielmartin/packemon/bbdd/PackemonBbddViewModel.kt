package com.hermosohermoso.danielmartin.packemon.bbdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PackemonBbddViewModel (private val packemonDao: PackemonDAO): ViewModel(){

//    La lista para mostrar los pokemon en la pokedex
private val _pokemonList = MutableStateFlow<List<PokemonDDBB>>(emptyList())
    val pokemonList: MutableStateFlow<List<PokemonDDBB>> = _pokemonList

//    fun recogerPokemons(): Flow<List<PokemonDDBB>> = packemonDao.recogerPokemons()

    fun recogerPokemons(isFav: Boolean) {
        // Suscribirse al Flow de la base de datos según el flag
        viewModelScope.launch {
            if (isFav){
                packemonDao.recogerPokemonsFav().collect {
                    _pokemonList.value = it
                }
            }else {
                packemonDao.recogerPokemons().collect {
                    _pokemonList.value = it
                }
            }
        }
    }

    suspend fun guardarPokemon(pokemonCard: PokemonDDBB) {
        viewModelScope.launch {
            packemonDao.guardarPokemon(pokemonCard)
        }
    }

    suspend fun actualizarFavorito(pokeId: String, isFav: Boolean) {
        viewModelScope.launch {
            packemonDao.actualizarFavorito(pokeId, isFav)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PackemonApplication)
                PackemonBbddViewModel(application.database.packemonDao())
            }
        }
    }
}