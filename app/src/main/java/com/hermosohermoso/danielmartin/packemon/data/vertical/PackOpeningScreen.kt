package com.hermosohermoso.danielmartin.packemon.data.vertical

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

@Composable
fun PackOpeningScreen(
    navController: NavHostController,
    uiState: PokeUiState,
    pokemonViewModel: PokemonViewModel
) {

//    if (uiState.isLoading) {
//        pokemonViewModel.pokemonPorVer()
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Image(
//                painter = painterResource(R.drawable.sobre_abierto),
//                contentDescription = "Sobre abierto",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(0.5f)
//            )
//        }


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo
            Image(
                painter = painterResource(R.drawable.sobre_abierto),
                contentDescription = "Sobre abierto",
                modifier = Modifier
                    .fillMaxSize()
            )

        // Spinner encima de la imagen si está cargando
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .wrapContentSize(Alignment.Center)
            ) {
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

