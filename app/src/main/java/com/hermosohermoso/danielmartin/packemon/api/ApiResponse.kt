@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package com.hermosohermoso.danielmartin.packemon.api

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val data: List<PokemonCard>
)

@Serializable
data class PokemonCard(
    val id: String,
    val name: String,
    val nationalPokedexNumbers: List<Int>?,
    val types: List<String>?,
    val images: CardImages,
    val attacks: List<Attack>,
    val set: SetInfo,
    var isFav: Boolean = false
)

@Serializable
data class CardImages(
    val small: String,
    val large: String
)

@Serializable
data class Attack(
    val name: String,
    val damage: String,
    val text: String
)
@Serializable
data class SetInfo(
    val id: String,
    val name: String,
    val series: String,
    val releaseDate: String,
    val images: SetImages
)

@Serializable
data class SetImages(
    val symbol: String,
    val logo: String
)