package com.hermosohermoso.danielmartin.packemon.data.vertical

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import java.lang.Character.toLowerCase

@Composable
fun PokemonDatosScreen(
    navController: NavController,
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier
) {
    // Recogemos el estado de los datos de Pokémon desde el ViewModel
    val pokemonMostrar = viewModel.obtenerPokemonMostrar()

    // Si no hay un Pokémon seleccionado, mostramos un mensaje
    if (pokemonMostrar == null) {
        Text(
            text = "Selecciona un Pokémon",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxSize(),
        )
        return
    }

    // Pantalla con la información del Pokémon
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Imagen del Pokémon
        AsyncImage(
            model = pokemonMostrar.images.large,
            contentDescription = pokemonMostrar.name,
            modifier = Modifier
                .size(500.dp)
                .padding(bottom = 12.dp)
        )
        // Información del set
        Text(
            text = pokemonMostrar.set.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 4.dp)
        )

        Text(
            text = "Release Date: ${pokemonMostrar.set.releaseDate}",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
        )

        AsyncImage(
            model = pokemonMostrar.set.images.logo,
            contentDescription = "Set Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(150.dp)
        )

        Button(
            onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
            modifier = Modifier
        ) {
            Text(text = stringResource(id = R.string.ir_pokedex))
        }
    }
}