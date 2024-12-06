package com.hermosohermoso.danielmartin.packemon.data.apaisado

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.bbdd.PokemonDDBB
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import kotlinx.coroutines.launch

@Composable
fun PokemonPulledApaisado(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
    bbddViewModel: PackemonBbddViewModel
) {
    val widthPantalla = LocalConfiguration.current.screenWidthDp.dp
    val pokemonMostrar = uiState.pokemonList.getOrNull(uiState.pokemonNumberShow)
    val coroutineScope = rememberCoroutineScope()
    val hayPokemonAnterior = if (uiState.pokemonNumberShow > 0) true else false
    val isFav = remember { mutableStateOf(pokemonMostrar?.isFav) }

    if (pokemonMostrar != null) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = pokemonMostrar.images.large,
                    contentDescription = pokemonMostrar.name,
                    modifier = Modifier
                        .aspectRatio(0.7f)
                        .fillMaxHeight()
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.poke_capturado))

                Button(
                    onClick = {
                        pokemonViewModel.restarAlContador()
                    },
                    enabled = hayPokemonAnterior,
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 16.dp)
                        .width(widthPantalla / 7)
                ) {
                    Text(stringResource(R.string.poke_anterior))
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            bbddViewModel.guardarPokemon(
                                PokemonDDBB(
                                    pokeId = pokemonMostrar.id,
                                    pokeName = pokemonMostrar.name,
                                    natioPNBbdd = pokemonMostrar.nationalPokedexNumbers?.get(0) ?: -1,
                                    pokeImgLarge = pokemonMostrar.images.large,
                                    pokeSetId = pokemonMostrar.set.id,
                                    pokeSetName = pokemonMostrar.set.name,
                                    pokeSetSeries = pokemonMostrar.set.series,
                                    pokeSetReleaseDate = pokemonMostrar.set.releaseDate,
                                    pokeSetLogo = pokemonMostrar.set.images.logo,
                                    isFav = false
                                )
                            )
                        }
                        isFav.value = false
                        pokemonViewModel.sumarAlContador()
                        pokemonViewModel.pokemonVistos()
                    },
                    modifier = Modifier
                        .width(widthPantalla / 7)
                ) {
                    Text(stringResource(R.string.poke_siguiente))
                }
            }
        }
    } else if (uiState.pokemonVistos){
        navController.navigate(PackemonScreens.SobreAbiertoCompleto.route)
    }

}




//                Row() {
////                    Text(
////                        text = pokemonMostrar.name,
////                        style = MaterialTheme.typography.displayMedium,
////                        modifier = Modifier.padding(bottom = 8.dp)
////                    )
//
////                    Image(
////                        painter = painterResource(mostrarImgFav(isFav.value!!)),
////                        contentDescription = "Favorito",
////                        modifier = Modifier
////                            .size(40.dp)
////                            .padding(start = 8.dp)
////                            .clickable {
////                                val favCambiado = !isFav.value!!
////                                isFav.value = favCambiado
////
////                                coroutineScope.launch {
////                                    bbddViewModel.guardarPokemon(
////                                        PokemonDDBB(
////                                            pokeId = pokemonMostrar.id,
////                                            pokeName = pokemonMostrar.name,
////                                            natioPNBbdd = pokemonMostrar.nationalPokedexNumbers?.get(0) ?: -1,
////                                            pokeImgLarge = pokemonMostrar.images.large,
////                                            pokeSetId = pokemonMostrar.set.id,
////                                            pokeSetName = pokemonMostrar.set.name,
////                                            pokeSetSeries = pokemonMostrar.set.series,
////                                            pokeSetReleaseDate = pokemonMostrar.set.releaseDate,
////                                            pokeSetLogo = pokemonMostrar.set.images.logo,
////                                            isFav = favCambiado
////                                        )
////                                    )
////                                }
////                                pokemonMostrar.isFav = favCambiado
////                            }
////                    )
//                }