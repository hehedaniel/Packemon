package com.hermosohermoso.danielmartin.packemon.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PackemonPreferences (private val dataStore: DataStore<Preferences>){
    private companion object{
        val NUM_POKEMON_FILA_MOSTRAR = intPreferencesKey("NUMPOKE")
    }

    val num_pokemon: Flow<Int> = dataStore.data.map {
        preferences -> preferences[NUM_POKEMON_FILA_MOSTRAR] ?: 1
    }

    suspend fun cambiarNumPokemon(numPokemon: Int){
        dataStore.edit {
            preferences -> preferences[NUM_POKEMON_FILA_MOSTRAR] = numPokemon
        }
    }
}