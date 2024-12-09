package com.hermosohermoso.danielmartin.packemon.ui.screens.apaisado

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hermosohermoso.danielmartin.packemon.model.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.navigation.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.model.PokemonViewModel

//Pantalla principal con:
//  El sobre cerrado
//  Boton abrir sobre
//  Boton ir a pokedex

@Composable
fun HomeScreenApaisado(
    navController: NavHostController,
    packemonUiState: PokeUiState,
    viewModel: PokemonViewModel,
) {

    val widthPantalla = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del sobre a la izquierda
        Image(
            painter = painterResource(R.drawable.sobrecerrado),
            contentDescription = "Sobre cerrado",
            modifier = Modifier
                .weight(0.5f)
                .padding(start = 16.dp)
                .fillMaxSize(0.9f)
        )

        // Columna con los botones a la derecha
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
                    viewModel.resetearContador()
                    viewModel.fetchPokemons()
                    viewModel.pokemonPorVer()
                    navController.navigate(PackemonScreens.SobreAbierto.route)
                },
                modifier = Modifier
                    .padding(bottom = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.secondary,
                    MaterialTheme.colorScheme.onSecondary
                ),
                contentPadding = PaddingValues(18.dp, 14.dp)
            ) {
                Text(text = stringResource(id = R.string.abrir_sobre))
            }
            Button(
                onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
                modifier = Modifier
                    .padding(bottom = 24.dp),
                colors = ButtonDefaults.buttonColors(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.colorScheme.onSecondary
                ),
            ) {
                Text(text = stringResource(id = R.string.ir_pokedex))
            }
        }
    }
}