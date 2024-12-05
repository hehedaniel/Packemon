package com.hermosohermoso.danielmartin.packemon.data.vertical

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
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
import com.hermosohermoso.danielmartin.packemon.mostrarImgFav
import kotlinx.coroutines.launch

@Composable
fun PokemonPulled(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
    bbddViewModel: PackemonBbddViewModel
) {

    val pokemonMostrar = uiState.pokemonList.getOrNull(uiState.pokemonNumberShow)
    val coroutineScope = rememberCoroutineScope()
    val hayPokemonAnterior = if (uiState.pokemonNumberShow > 0) true else false
    val estaEnFavorito = remember { mutableStateOf(false) }

    if (pokemonMostrar != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.poke_capturado),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(top = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pokemonMostrar.name,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .weight(1f) // Deja espacio para la imagen de favorito
                )

                Image(
                    painter = painterResource(mostrarImgFav(estaEnFavorito.value)),
                    contentDescription = "Favorito",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            // Cambiar el estado de favorito y actualizar la base de datos
                            estaEnFavorito.value = !estaEnFavorito.value
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
                                        isFav = true
                                    )
                                )
                            }
                        }
                )
            }

            AsyncImage(
                model = pokemonMostrar.images.large,
                contentDescription = pokemonMostrar.name,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(bottom = 12.dp)
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
                    Text("Anterior Pokémon")
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
                        pokemonViewModel.sumarAlContador()
                        estaEnFavorito.value = false
                        Log.d("PokemonViewModel", uiState.pokemonNumberShow.toString())
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Siguiente Pokémon")
                }
            }
        }
    } else {
        navController.navigate(PackemonScreens.SobreAbiertoCompleto.route)
    }
}