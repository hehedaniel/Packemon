package com.hermosohermoso.danielmartin.packemon.data.vertical

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

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

        if (pokemonNumberShow == 0) {
            navController.navigate(PackemonScreens.PokeObtenidos.route)
        } else {
//            Log.d("PokemonViewModel", "No quedan más Pokémon para mostrar")
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    uiState.pokemonList.forEach { pokemon ->
                        item {
                            PokemonCardShow(pokemon = pokemon)
                        }
                    }
                }

                Button(
                    onClick = {
                        navController.navigate(PackemonScreens.Start.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentPadding = PaddingValues(18.dp, 14.dp)
                ) {
                    Text(text = stringResource(id = R.string.ir_sobre))
                }
            }

        }
    }
}

@Composable
fun PokemonCardShow(pokemon: PokemonCard) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
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