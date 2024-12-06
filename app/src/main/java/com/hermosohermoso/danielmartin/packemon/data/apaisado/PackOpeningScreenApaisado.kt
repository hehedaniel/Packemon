package com.hermosohermoso.danielmartin.packemon.data.apaisado

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
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
fun PackOpeningScreenApaisado(
    navController: NavHostController,
    uiState: PokeUiState,
    pokemonViewModel: PokemonViewModel
) {
//    Text("Pack opening screen")

    Box(modifier = Modifier.fillMaxSize()){
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .wrapContentSize(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(R.drawable.sobre_abierto),
                    contentDescription = "Sobre abierto",
                    modifier = Modifier
                        .fillMaxSize()
                )

                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp),
                    strokeWidth = 8.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                )
            }
        } else if(uiState.pokemonNumberShow == -1) {
            pokemonViewModel.sumarAlContador()
            navController.navigate(PackemonScreens.PokeObtenidos.route)
        }
    }
}

//@Composable
//fun PokemonCardShow(pokemon: PokemonCard) {
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(8.dp)) {
//        AsyncImage(
//            model = pokemon.images.large,
//            contentDescription = pokemon.name,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp)
//                .padding(4.dp)
//        )
//    }
//}

