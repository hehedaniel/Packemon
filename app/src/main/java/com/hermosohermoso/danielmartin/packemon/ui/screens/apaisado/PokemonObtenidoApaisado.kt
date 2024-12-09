package com.hermosohermoso.danielmartin.packemon.ui.screens.apaisado

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hermosohermoso.danielmartin.packemon.model.PokemonViewModel
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.model.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.model.PokemonDDBB
import com.hermosohermoso.danielmartin.packemon.navigation.PackemonScreens
import kotlinx.coroutines.launch

@Composable
fun PokemonPulledApaisado(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
    bbddViewModel: PackemonBbddViewModel
) {
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
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = pokemonMostrar.name,
                        style = MaterialTheme.typography.displayLarge,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Button(
                    onClick = {
                        pokemonViewModel.restarAlContador()
                    },
                    enabled = hayPokemonAnterior,
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 16.dp)
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
                ) {
                    Text(stringResource(R.string.poke_siguiente))
                }
            }
        }
    } else if (uiState.pokemonVistos){
        navController.navigate(PackemonScreens.SobreAbiertoCompleto.route)
    }

}

