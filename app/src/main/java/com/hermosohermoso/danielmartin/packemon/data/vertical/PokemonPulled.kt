package com.hermosohermoso.danielmartin.packemon.data.vertical

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonDataBase
import com.hermosohermoso.danielmartin.packemon.bbdd.PokemonDDBB
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import kotlinx.coroutines.coroutineScope
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

//    Para guardarlo en la bbdd

    if (pokemonMostrar != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Pokémon capturado:")
            Text(
                text = pokemonMostrar.name,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            AsyncImage(
                model = pokemonMostrar.images.large,
                contentDescription = pokemonMostrar.name,
                modifier = Modifier
                    .size(500.dp)
                    .padding(bottom = 12.dp)
            )

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
                                pokeSetLogo = pokemonMostrar.set.images.logo
                            )
                        )
                    }
                    pokemonViewModel.sumarAlContador()
                    navController.navigate(PackemonScreens.PokeObtenidos.route)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Siguiente Pokémon")
            }
        }
    } else {
//        Log.d("PokemonViewModel", "No hay más Pokémon disponibles")
        navController.navigate(PackemonScreens.SobreAbierto.route)
    }
}
