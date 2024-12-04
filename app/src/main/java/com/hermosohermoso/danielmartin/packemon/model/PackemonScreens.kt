package com.hermosohermoso.danielmartin.packemon.model

import com.hermosohermoso.danielmartin.packemon.R

enum class PackemonScreens(val route: String, val title: Int) {
    Start("Start", R.string.app_name),
    SobreAbierto("SobreAbierto", R.string.sobre_abierto),
    PokeObtenidos("PokeObtenidos", R.string.poke_obtenidos),
    Pokedex("Pokedex", R.string.pokedex),
    PokemonDatos("PokemonDatos", R.string.pokemon_datos)
}