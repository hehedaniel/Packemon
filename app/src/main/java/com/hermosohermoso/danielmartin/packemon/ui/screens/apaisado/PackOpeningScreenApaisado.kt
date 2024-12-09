package com.hermosohermoso.danielmartin.packemon.ui.screens.apaisado

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hermosohermoso.danielmartin.packemon.model.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.navigation.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.model.PokemonViewModel

@Composable
fun PackOpeningScreenApaisado(
    navController: NavHostController,
    uiState: PokeUiState,
    pokemonViewModel: PokemonViewModel
) {
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