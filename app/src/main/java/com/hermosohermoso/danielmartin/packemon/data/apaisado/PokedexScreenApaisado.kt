package com.hermosohermoso.danielmartin.packemon.data.apaisado

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.PokeUiState
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

@Composable
fun PokedexApaisado(
    navController: NavController,
    viewModel: PokemonViewModel,
    bbddViewModel: PackemonBbddViewModel,
    packemonUiState: PokeUiState,
    modifier: Modifier = Modifier
){
    var mostrarFavoritos by remember { mutableStateOf(false) }
    val pokemonList by bbddViewModel.pokemonList.collectAsState()
    bbddViewModel.recogerPokemons(mostrarFavoritos)


    val widthPantalla = LocalConfiguration.current.screenWidthDp.dp

    //    Obtengo el numero de las preferencias de usuario
    val pokeNumGrid = packemonUiState.num_pokemon_fila
    var pokeGrid: Int
    var idDrawable: Int

    when (pokeNumGrid) {
        1 -> {
            pokeGrid = 2
            idDrawable = R.drawable.dos
        }
        2 -> {
            pokeGrid = 3
            idDrawable = R.drawable.tres
        }
        3 -> {
            pokeGrid = 4
            idDrawable = R.drawable.cuatro
        }
        else -> {
            pokeGrid = 6
            idDrawable = R.drawable.sesis
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Imagen del icono alineado a la derecha
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                painter = painterResource(id = idDrawable),
                contentDescription = stringResource(id = R.string.dos),
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        viewModel.cambiarNumPokemon()
                    }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(pokeGrid),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            items(pokemonList) { pokemon ->
                mostrarPokemonItemApaisado(
                    imgPokemon = pokemon.pokeImgLarge,
                    nombrePokemon = pokemon.pokeName,
                    imgHeight = 200,
                    onClick = {
                        viewModel.guardarPokemonMostrar(pokemon)
                        navController.navigate(PackemonScreens.PokemonDatos.route)
                    },
                    widthCarta = widthPantalla / pokeGrid
                )
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
fun mostrarPokemonItemApaisado(
    imgPokemon: String,
    nombrePokemon: String,
    imgHeight: Int,
    onClick: () -> Unit,
    widthCarta: Dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(widthCarta)
            .aspectRatio(0.8f)
            .clickable { onClick() }
            .padding(2.dp)
    ) {
        AsyncImage(
            model = imgPokemon,
            contentDescription = nombrePokemon,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
    }
}