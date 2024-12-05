package com.hermosohermoso.danielmartin.packemon.data.vertical

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

//Pantalla principal con:
//  El sobre cerrado
//  Boton abrir sobre
//  Boton ir a pokedex

@Composable
fun HomeScreen(
    navController: NavHostController,
    packemonUiState: PokeUiState,
    viewModel: PokemonViewModel,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.sobrecerrado),
            contentDescription = "Sobre cerrado",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        )
        Button(
            onClick = {
                viewModel.resetearContador()
                viewModel.fetchPokemons()
                navController.navigate(PackemonScreens.SobreAbierto.route)
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp),
            contentPadding = PaddingValues(18.dp, 14.dp),
            colors = ButtonDefaults.buttonColors(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.onSecondary
            ),
        ) {
            Text(text = stringResource(id = R.string.abrir_sobre))
        }
        Button(
            onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
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