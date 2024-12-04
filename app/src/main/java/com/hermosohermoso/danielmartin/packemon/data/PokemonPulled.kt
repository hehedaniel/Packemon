package com.hermosohermoso.danielmartin.packemon.data

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.hermosohermoso.danielmartin.packemon.old.RetrofitInstance
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens

@Composable
fun PokemonPulled(
    navController: NavController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState,
) {
    val pokemonMostrar = uiState.pokemonList.getOrNull(uiState.pokemonNumberShow)

    Log.d("PokemonViewModel", "Mostrandooo Pokémon ${pokemonMostrar?.name}")
    Log.d("PokemonViewModel", "Mostrando pokemon ${pokemonMostrar != null}")


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
                    pokemonViewModel.sumarAlContador()
                    navController.navigate(PackemonScreens.SobreAbierto.route)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Siguiente Pokémon")
            }
        }
    } else {
        // Si no hay más Pokémon para mostrar (el contador ha llegado al límite), mostramos un mensaje
        Log.d("PokemonViewModel", "No hay más Pokémon disponibles")
        Text("No hay más Pokémon disponibles")
    }
}


// Información de los ataques
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                pokemonMostrar.attacks?.get(0)?.let {
//                    Text(
//                        text = it.text,
//                        style = MaterialTheme.typography.displaySmall,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//                pokemonMostrar.attacks?.get(1)?.let {
//                    Text(
//                        text = it.text,
//                        style = MaterialTheme.typography.displaySmall,
//                        modifier = Modifier.padding(bottom = 8.dp)
//                    )
//                }
//            }
