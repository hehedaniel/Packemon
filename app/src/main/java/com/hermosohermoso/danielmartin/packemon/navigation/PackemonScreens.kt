package com.hermosohermoso.danielmartin.packemon.navigation

import com.hermosohermoso.danielmartin.packemon.R

enum class PackemonScreens(val route: String, val title: Int) {
//    Ruta de inicio, con el sobre
    Start("Start", R.string.app_name),
//    Ruta con el sobre rajao
    SobreAbierto("SobreAbierto", R.string.sobre_abierto),
//    Ruta con el pokemon obtenido 
    PokeObtenidos("PokeObtenidos", R.string.poke_obtenidos),
//    Ruta con la pokedex
    Pokedex("Pokedex", R.string.pokedex),
//    Ruta con los datos del pokemon
    PokemonDatos("PokemonDatos", R.string.pokemon_datos),
//    RUta con el sobre abierto
    SobreAbiertoCompleto("SobreAbiertoCompleto", R.string.sobre_abierto_completo)
}