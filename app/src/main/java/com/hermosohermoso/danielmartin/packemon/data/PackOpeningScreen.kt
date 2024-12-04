package com.hermosohermoso.danielmartin.packemon.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PackOpeningScreen(
    navController: NavHostController,
    uiState: PokeUiState,
    viewModel: PokemonViewModel
) {
    Text("Pack opening screen")

    if (uiState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.sobre_abierto),
                contentDescription = "Sobre abierto",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
            )
        }
        Log.d("PokemonViewModel", "Recibiendo datos de la api")

    } else {
        val pokemonNumberShow = uiState.pokemonNumberShow
        val pokemonSiguiente = uiState.pokemonList.getOrNull(pokemonNumberShow)

        if (pokemonSiguiente != null) {
            navController.navigate(PackemonScreens.PokeObtenidos.route)
        } else {
                Log.d("PokemonViewModel", "No quedan más Pokémon para mostrar")
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    uiState.pokemonList.forEach { pokemon ->
                        item {
                            PokemonCardShow(pokemon = pokemon)
                        }
                    }
                }
            }
        }
}

@Composable
fun PokemonCardShow(pokemon: PokemonCard) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        AsyncImage(
            model = pokemon.images.large,
            contentDescription = pokemon.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .padding(4.dp)
        )
    }
}


//    // Llamamos a la función fetchPokemons() para obtener las cartas
//    pokemonViewModel.fetchPokemons()
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Image(
//            painter = painterResource(R.drawable.sobre_abierto),
//            contentDescription = "Sobre abierto",
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(0.5f)
//        )
//    }
//
//    // Navegar a la siguiente pantalla cuando se obtienen los pokemons
//    navController.navigate(PackemonScreens.SobreAbierto.route)