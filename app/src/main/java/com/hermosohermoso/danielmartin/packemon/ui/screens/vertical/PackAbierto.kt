package com.hermosohermoso.danielmartin.packemon.ui.screens.vertical

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
fun PackOpened(
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
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        ){
            Button(
                onClick = {
                    navController.navigate(PackemonScreens.Start.route)
                },
                modifier = Modifier
//                .fillMaxWidth()
                    .padding(16.dp),
                contentPadding = PaddingValues(18.dp, 14.dp)
            ) {
                Text(text = stringResource(id = R.string.ir_sobre))
            }
        }
    }
}

@Composable
fun PokemonCardShow(pokemon: PokemonCard) {
    Log.d("soy yo", pokemon.name)
    Column(
        modifier = Modifier
            .fillMaxWidth(0.3f)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = pokemon.images.large,
            contentDescription = pokemon.name,
            modifier = Modifier
                .fillMaxWidth()
//                .height(225.dp)
                .padding(4.dp)
        )
    }
}