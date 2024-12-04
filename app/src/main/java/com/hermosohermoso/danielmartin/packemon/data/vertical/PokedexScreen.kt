package com.hermosohermoso.danielmartin.packemon.data.vertical

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hermosohermoso.danielmartin.packemon.R
import com.hermosohermoso.danielmartin.packemon.api.Attack
import com.hermosohermoso.danielmartin.packemon.api.CardImages
import com.hermosohermoso.danielmartin.packemon.api.PokemonCard
import com.hermosohermoso.danielmartin.packemon.api.SetImages
import com.hermosohermoso.danielmartin.packemon.api.SetInfo
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

@Composable
fun Pokedex(
    navController: NavController,
    viewModel: PokemonViewModel,
    modifier: Modifier = Modifier
){



    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(pokemonList) { pokemon ->
            mostrarPokemonItem(
                imgPokemon = pokemon.images.large,
                nombrePokemon = pokemon.name,
                onClick = {
                    viewModel.guardarPokemonMostrar(pokemon)
                    navController.navigate(PackemonScreens.PokemonDatos.route)
                }
            )
        }
    }
    Button(
        onClick = { navController.navigate(PackemonScreens.Start.route) },
        modifier = Modifier
            .padding(bottom = 24.dp)
    ) {
        Text(text = stringResource(id = R.string.ir_sobre))
    }
}

@Composable
fun mostrarPokemonItem(
    imgPokemon: String,
    nombrePokemon: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() }
            .padding(2.dp)
    ) {
        AsyncImage(
            model = imgPokemon,
            contentDescription = nombrePokemon,
            modifier = Modifier
                .fillMaxWidth()
                .height(225.dp)
                .padding(4.dp)
        )
    }
}


val pokemonListPrueba = listOf(
            PokemonCard(
                id = "dp3-1",
                name = "Ampharos",
                nationalPokedexNumbers = listOf(181),
                types = listOf("Lightning"),
                images = CardImages(
                    small = "https://images.pokemontcg.io/dp3/1.png",
                    large = "https://images.pokemontcg.io/dp3/1_hires.png"
                ),
                attacks = listOf(
                    Attack(
                        name = "Cluster Bolt",
                        damage = "70",
                        text = "You may discard all Lightning Energy attached to Ampharos..."
                    )
                ),
                set = SetInfo(
                    id = "dp3",
                    name = "Diamond & Pearl",
                    series = "Diamond & Pearl",
                    releaseDate = "2007/05/01",
                    images = SetImages(
                        symbol = "https://images.pokemontcg.io/dp3/symbol.png",
                        logo = "https://images.pokemontcg.io/dp3/logo.png"
                    )
                )
            ),
            PokemonCard(
                id = "dp3-2",
                name = "Blastoise",
                nationalPokedexNumbers = listOf(9),
                types = listOf("Water"),
                images = CardImages(
                    small = "https://images.pokemontcg.io/dp3/2.png",
                    large = "https://images.pokemontcg.io/dp3/2_hires.png"
                ),
                attacks = listOf(
                    Attack(
                        name = "Hydro Pump",
                        damage = "60",
                        text = "Discard 1 Water Energy attached to Blastoise."
                    )
                ),
                set = SetInfo(
                    id = "dp3",
                    name = "Diamond & Pearl",
                    series = "Diamond & Pearl",
                    releaseDate = "2007/05/01",
                    images = SetImages(
                        symbol = "https://images.pokemontcg.io/dp3/symbol.png",
                        logo = "https://images.pokemontcg.io/dp3/logo.png"
                    )
                )
            ),
            PokemonCard(
                id = "dp3-3",
                name = "Charizard",
                nationalPokedexNumbers = listOf(6),
                types = listOf("Fire"),
                images = CardImages(
                    small = "https://images.pokemontcg.io/dp3/3.png",
                    large = "https://images.pokemontcg.io/dp3/3_hires.png"
                ),
                attacks = listOf(
                    Attack(
                        name = "Fire Spin",
                        damage = "100",
                        text = "Discard 2 Fire Energy attached to Charizard."
                    )
                ),
                set = SetInfo(
                    id = "dp3",
                    name = "Diamond & Pearl",
                    series = "Diamond & Pearl",
                    releaseDate = "2007/05/01",
                    images = SetImages(
                        symbol = "https://images.pokemontcg.io/dp3/symbol.png",
                        logo = "https://images.pokemontcg.io/dp3/logo.png"
                    )
                )
            ),
            PokemonCard(
                id = "dp3-4",
                name = "Pikachu",
                nationalPokedexNumbers = listOf(25),
                types = listOf("Electric"),
                images = CardImages(
                    small = "https://images.pokemontcg.io/dp3/4.png",
                    large = "https://images.pokemontcg.io/dp3/4_hires.png"
                ),
                attacks = listOf(
                    Attack(
                        name = "Thunderbolt",
                        damage = "50",
                        text = "Discard all Lightning Energy attached to Pikachu."
                    )
                ),
                set = SetInfo(
                    id = "dp3",
                    name = "Diamond & Pearl",
                    series = "Diamond & Pearl",
                    releaseDate = "2007/05/01",
                    images = SetImages(
                        symbol = "https://images.pokemontcg.io/dp3/symbol.png",
                        logo = "https://images.pokemontcg.io/dp3/logo.png"
                    )
                )
            )
)

