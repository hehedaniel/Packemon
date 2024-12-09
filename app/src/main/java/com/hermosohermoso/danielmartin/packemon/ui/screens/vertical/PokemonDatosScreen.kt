package com.hermosohermoso.danielmartin.packemon.ui.screens.vertical

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.model.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.navigation.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.navigation.mostrarImgFav
import com.hermosohermoso.danielmartin.packemon.model.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun PokemonDatosScreen(
    navController: NavController,
    viewModel: PokemonViewModel,
    bbddViewModel: PackemonBbddViewModel,
    modifier: Modifier = Modifier
) {
    // Recogemos el estado de los datos de Pokémon desde el ViewModel
    val pokemonMostrar = viewModel.obtenerPokemonMostrar()
    val coroutineScope = rememberCoroutineScope()
    val openAlertDialog = remember { mutableStateOf(false) }

    // Si no hay un Pokémon seleccionado, mostramos un mensaje
    if (pokemonMostrar == null) {
        Text(
            text = "Selecciona un Pokémon",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxSize(),
        )
        return
    }

    var imgFavorito = remember { mutableStateOf(pokemonMostrar.isFav) }

    // Pantalla con la información del Pokémon
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Image(
            painter = painterResource(mostrarImgFav(imgFavorito.value)),
            contentDescription = "Favorito",
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd)
                .padding(bottom = 18.dp)
                .clickable {
                    imgFavorito.value = !imgFavorito.value
                    coroutineScope.launch {
                        bbddViewModel.actualizarFavorito(pokemonMostrar.pokeId, !pokemonMostrar.isFav)
                    }
                }
        )

        // Contenedor para la información del Pokémon
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagen del Pokémon
            AsyncImage(
                model = pokemonMostrar.pokeImgLarge,
                contentDescription = pokemonMostrar.pokeName,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            )
            // Información del set
            Text(
                text = pokemonMostrar.pokeSetName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 4.dp)
            )

            Text(
                text = "Release Date: ${pokemonMostrar.pokeSetReleaseDate}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
            )

            AsyncImage(
                model = pokemonMostrar.pokeSetLogo,
                contentDescription = "Set Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .size(150.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
                    modifier = Modifier
                ) {
                    Text(text = stringResource(id = R.string.ir_pokedex))
                }
                Button(
                    onClick = {
                        openAlertDialog.value = true
                    },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.onError
                    ),
                ) {
                    Text(text = stringResource(id = R.string.liberar))
                }
            }

        }
    }
}