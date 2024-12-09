package com.hermosohermoso.danielmartin.packemon.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonDDBB(
    @PrimaryKey(autoGenerate = false)
    val pokeId: String,
    @ColumnInfo(name = "pokeName")
    val pokeName: String,
    @ColumnInfo(name = "natioPn")
    val natioPNBbdd: Int,
    @ColumnInfo(name = "pokeImgLarge")
    val pokeImgLarge: String,
    @ColumnInfo(name = "pokeSetId")
    val pokeSetId: String,
    @ColumnInfo(name = "pokeSetName")
    val pokeSetName: String,
    @ColumnInfo(name = "pokeSetSeries")
    val pokeSetSeries: String,
    @ColumnInfo(name = "pokeSetReleaseDate")
    val pokeSetReleaseDate: String,
    @ColumnInfo(name = "pokeSetLogo")
    val pokeSetLogo: String,
    @ColumnInfo(name = "fav")
    val isFav: Boolean
)