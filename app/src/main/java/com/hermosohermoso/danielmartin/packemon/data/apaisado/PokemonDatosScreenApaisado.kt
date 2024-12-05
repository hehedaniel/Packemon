package com.hermosohermoso.danielmartin.packemon.data.apaisado

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import kotlinx.coroutines.launch

@Composable
fun PokemonDatosScreenApaisado(
    navController: NavController,
    viewModel: PokemonViewModel,
    bbddViewModel: PackemonBbddViewModel,
    modifier: Modifier = Modifier
) {
    val pokemonMostrar = viewModel.obtenerPokemonMostrar()
    val coroutineScope = rememberCoroutineScope()

    if (pokemonMostrar == null) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Selecciona un Pokémon",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        return
    }

    var imgFavorito = remember { mutableStateOf(pokemonMostrar.isFav) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {


        // Imagen del Pokémon
        AsyncImage(
            model = pokemonMostrar.pokeImgLarge,
            contentDescription = pokemonMostrar.pokeName,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        )

        // Columna para la información y el botón
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(mostrarImgFav(imgFavorito.value)),
                contentDescription = "Favorito",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
                    .padding(bottom = 18.dp)
                    .clickable {
                        imgFavorito.value = !imgFavorito.value
                        coroutineScope.launch {
                            bbddViewModel.actualizarFavorito(pokemonMostrar.pokeId, !pokemonMostrar.isFav)
                        }
                    }
            )
            // Información del set
            Text(
                text = pokemonMostrar.pokeSetName,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Release Date: ${pokemonMostrar.pokeSetReleaseDate}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            // Logo del set
            AsyncImage(
                model = pokemonMostrar.pokeSetLogo,
                contentDescription = "Set Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(100.dp)
                    .padding(bottom = 16.dp)
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            ){
                Button(
                    onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
                ) {
                    Text(text = stringResource(id = R.string.ir_pokedex))
                }
            }
        }
    }
}


fun mostrarImgFav(estado: Boolean): Int{
    if (estado == true) {
        return R.drawable.favoritomarcado
    } else {
        return R.drawable.favorito
    }
}