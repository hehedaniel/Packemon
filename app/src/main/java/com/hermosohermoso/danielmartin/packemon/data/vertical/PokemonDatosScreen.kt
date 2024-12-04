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
        Text(
            text = pokemonMostrar.name,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Imagen del Pokémon
        AsyncImage(
            model = pokemonMostrar.images.large,
            contentDescription = pokemonMostrar.name,
            modifier = Modifier
                .size(500.dp)
                .padding(bottom = 12.dp)
        )

        // Imagen del tipo del Pokémon
        Image(
            painter = painterResource(id = obtenerDrawableTipo(
                pokemonMostrar.types?.firstOrNull() ?: "fire")),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 24.dp)
        )

        Log.d("Pokeataques", pokemonMostrar.attacks.toString())
        // Información de los ataques
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Text(
//                text = pokemonMostrar.attacks[0].text,
//                style = MaterialTheme.typography.displaySmall,
//                modifier = Modifier.padding(bottom = 8.dp)
//            )
//            Text(
//                text = pokemonMostrar.attacks[1].text,
//                style = MaterialTheme.typography.displaySmall
//            )
//        }
        Button(
            onClick = { navController.navigate(PackemonScreens.Pokedex.route) },
            modifier = Modifier
//            .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp)
        ) {
            Text(text = stringResource(id = R.string.ir_pokedex))
        }
    }
}

fun obtenerDrawableTipo(tipo: String): Int {
    return when (tipo.lowercase()) {
        "dragon" -> R.drawable.dragon
        "lightning" -> R.drawable.electric
        "fairy" -> R.drawable.fairy
        "fighting" -> R.drawable.fighting
        "fire" -> R.drawable.fire
        "flying" -> R.drawable.flying
        "ghost" -> R.drawable.ghost
        "grass" -> R.drawable.grass
        "ground" -> R.drawable.ground
        "ice" -> R.drawable.ice
        "normal" -> R.drawable.normal
        "poison" -> R.drawable.poison
        "psychic" -> R.drawable.psychic
        "rock" -> R.drawable.rock
        "steel" -> R.drawable.steel
        "water" -> R.drawable.water
        else -> R.drawable.colorlesss
    }
}
