package com.hermosohermoso.danielmartin.packemon.ui.screens.apaisado

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.model.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PokemonCard
import com.hermosohermoso.danielmartin.packemon.navigation.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.model.PokemonViewModel

@Composable
fun PackOpenedApaisado(
    navController: NavHostController,
    pokemonViewModel: PokemonViewModel,
    uiState: PokeUiState
){
    pokemonViewModel.pokemonPorVer()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyHorizontalGrid (
            rows = GridCells.Fixed(1),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            uiState.pokemonList.forEach { pokemon ->
                item {
                    PokemonCardShowApaisado(pokemon = pokemon)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.Center
        ){
            Button(
                onClick = { navController.navigate(PackemonScreens.Start.route) },
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.ir_sobre))
            }
        }
    }
}

@Composable
fun PokemonCardShowApaisado(pokemon: PokemonCard) {

        AsyncImage(
            model = pokemon.images.large,
            contentDescription = pokemon.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
}