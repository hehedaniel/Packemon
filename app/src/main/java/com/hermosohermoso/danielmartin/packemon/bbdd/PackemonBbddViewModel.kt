package com.hermosohermoso.danielmartin.packemon.bbdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class PackemonBbddViewModel (private val packemonDao: PackemonDAO): ViewModel(){
    fun recogerPokemons(): Flow<List<PokemonDDBB>> = packemonDao.recogerPokemons()

    suspend fun guardarPokemon(pokemonCard: PokemonDDBB) {
        viewModelScope.launch {
            packemonDao.guardarPokemon(pokemonCard)
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