package com.hermosohermoso.danielmartin.packemon.data.apaisado

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens

@Composable
fun PokemonPulledApaisado(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
) {
    val pokemonMostrar = uiState.pokemonList.getOrNull(uiState.pokemonNumberShow)

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
                        .size(250.dp)
                        .padding(end = 16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Pokémon capturado:")
                Text(
                    text = pokemonMostrar.name,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Button(
                    onClick = {
                        pokemonViewModel.sumarAlContador()
                        navController.navigate(PackemonScreens.PokeObtenidos.route)
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Siguiente Pokémon")
                }
            }
        }
    } else {
        Log.d("PokemonViewModel", "No hay más Pokémon disponibles")
        navController.navigate(PackemonScreens.SobreAbierto.route)
    }

}
