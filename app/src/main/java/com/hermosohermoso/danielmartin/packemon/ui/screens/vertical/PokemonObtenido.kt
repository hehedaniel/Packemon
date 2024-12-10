package com.hermosohermoso.danielmartin.packemon.ui.screens.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
fun PokemonPulled(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
    bbddViewModel: PackemonBbddViewModel
) {
    var pokemonMostrar = uiState.pokemonList.getOrNull(uiState.pokemonNumberShow)
    val coroutineScope = rememberCoroutineScope()
    val hayPokemonAnterior = if (uiState.pokemonNumberShow > 0) true else false
    val isFav = remember { mutableStateOf(pokemonMostrar?.isFav) }

    if (pokemonMostrar != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp,bottom = 12.dp),
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

            AsyncImage(
                model = pokemonMostrar.images.large,
                contentDescription = pokemonMostrar.name,
                placeholder = painterResource(R.drawable.placeholder_pokeball),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp, top = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = {
                        pokemonViewModel.restarAlContador()
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    enabled = hayPokemonAnterior
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
                                    isFav = pokemonMostrar.isFav
                                )
                            )
                        }
                        isFav.value = false
                        pokemonViewModel.sumarAlContador()
                        pokemonViewModel.pokemonVistos()
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(stringResource(R.string.poke_siguiente))
                }
            }
        }
    } else if (uiState.pokemonVistos){
        navController.navigate(PackemonScreens.SobreAbiertoCompleto.route)
    }
}