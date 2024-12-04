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
//            .align(Alignment.CenterHorizontally)
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


val pokemonList = listOf(
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
        )
    ),
    PokemonCard(
        id = "dp3-5",
        name = "Venusaur",
        nationalPokedexNumbers = listOf(3),
        types = listOf("Grass", "Poison"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/5.png",
            large = "https://images.pokemontcg.io/dp3/5_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "SolarBeam",
                damage = "80",
                text = "Requires 2 Grass Energy and 1 Colorless."
            )
        )
    ),
    PokemonCard(
        id = "dp3-6",
        name = "Snorlax",
        nationalPokedexNumbers = listOf(143),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/6.png",
            large = "https://images.pokemontcg.io/dp3/6_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Body Slam",
                damage = "60",
                text = "Flip a coin. If heads, the defending Pokémon is now Paralyzed."
            )
        )
    ),
    PokemonCard(
        id = "dp3-7",
        name = "Gengar",
        nationalPokedexNumbers = listOf(94),
        types = listOf("Ghost"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/7.png",
            large = "https://images.pokemontcg.io/dp3/7_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Shadow Ball",
                damage = "60",
                text = "You may discard any number of Psychic Energy attached to Gengar."
            )
        )
    ),
    PokemonCard(
        id = "dp3-8",
        name = "Alakazam",
        nationalPokedexNumbers = listOf(65),
        types = listOf("Psychic"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/8.png",
            large = "https://images.pokemontcg.io/dp3/8_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Psychic",
                damage = "50",
                text = "This attack does 10 more damage for each Psychic Energy attached to Alakazam."
            )
        )
    ),
    PokemonCard(
        id = "dp3-9",
        name = "Machamp",
        nationalPokedexNumbers = listOf(68),
        types = listOf("Fighting"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/9.png",
            large = "https://images.pokemontcg.io/dp3/9_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Submission",
                damage = "60",
                text = "This attack does 10 damage to Machamp."
            )
        )
    ),
    PokemonCard(
        id = "dp3-10",
        name = "Blissey",
        nationalPokedexNumbers = listOf(113),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/10.png",
            large = "https://images.pokemontcg.io/dp3/10_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Softboiled",
                damage = "0",
                text = "Heal 50 damage from one of your Pokémon."
            )
        )
    ),
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
        )
    ),
    PokemonCard(
        id = "dp3-5",
        name = "Venusaur",
        nationalPokedexNumbers = listOf(3),
        types = listOf("Grass", "Poison"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/5.png",
            large = "https://images.pokemontcg.io/dp3/5_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "SolarBeam",
                damage = "80",
                text = "Requires 2 Grass Energy and 1 Colorless."
            )
        )
    ),
    PokemonCard(
        id = "dp3-6",
        name = "Snorlax",
        nationalPokedexNumbers = listOf(143),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/6.png",
            large = "https://images.pokemontcg.io/dp3/6_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Body Slam",
                damage = "60",
                text = "Flip a coin. If heads, the defending Pokémon is now Paralyzed."
            )
        )
    ),
    PokemonCard(
        id = "dp3-7",
        name = "Gengar",
        nationalPokedexNumbers = listOf(94),
        types = listOf("Ghost"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/7.png",
            large = "https://images.pokemontcg.io/dp3/7_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Shadow Ball",
                damage = "60",
                text = "You may discard any number of Psychic Energy attached to Gengar."
            )
        )
    ),
    PokemonCard(
        id = "dp3-8",
        name = "Alakazam",
        nationalPokedexNumbers = listOf(65),
        types = listOf("Psychic"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/8.png",
            large = "https://images.pokemontcg.io/dp3/8_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Psychic",
                damage = "50",
                text = "This attack does 10 more damage for each Psychic Energy attached to Alakazam."
            )
        )
    ),
    PokemonCard(
        id = "dp3-9",
        name = "Machamp",
        nationalPokedexNumbers = listOf(68),
        types = listOf("Fighting"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/9.png",
            large = "https://images.pokemontcg.io/dp3/9_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Submission",
                damage = "60",
                text = "This attack does 10 damage to Machamp."
            )
        )
    ),
    PokemonCard(
        id = "dp3-10",
        name = "Blissey",
        nationalPokedexNumbers = listOf(113),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/10.png",
            large = "https://images.pokemontcg.io/dp3/10_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Softboiled",
                damage = "0",
                text = "Heal 50 damage from one of your Pokémon."
            )
        )
    ),
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
        )
    ),
    PokemonCard(
        id = "dp3-5",
        name = "Venusaur",
        nationalPokedexNumbers = listOf(3),
        types = listOf("Grass", "Poison"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/5.png",
            large = "https://images.pokemontcg.io/dp3/5_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "SolarBeam",
                damage = "80",
                text = "Requires 2 Grass Energy and 1 Colorless."
            )
        )
    ),
    PokemonCard(
        id = "dp3-6",
        name = "Snorlax",
        nationalPokedexNumbers = listOf(143),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/6.png",
            large = "https://images.pokemontcg.io/dp3/6_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Body Slam",
                damage = "60",
                text = "Flip a coin. If heads, the defending Pokémon is now Paralyzed."
            )
        )
    ),
    PokemonCard(
        id = "dp3-7",
        name = "Gengar",
        nationalPokedexNumbers = listOf(94),
        types = listOf("Ghost"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/7.png",
            large = "https://images.pokemontcg.io/dp3/7_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Shadow Ball",
                damage = "60",
                text = "You may discard any number of Psychic Energy attached to Gengar."
            )
        )
    ),
    PokemonCard(
        id = "dp3-8",
        name = "Alakazam",
        nationalPokedexNumbers = listOf(65),
        types = listOf("Psychic"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/8.png",
            large = "https://images.pokemontcg.io/dp3/8_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Psychic",
                damage = "50",
                text = "This attack does 10 more damage for each Psychic Energy attached to Alakazam."
            )
        )
    ),
    PokemonCard(
        id = "dp3-9",
        name = "Machamp",
        nationalPokedexNumbers = listOf(68),
        types = listOf("Fighting"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/9.png",
            large = "https://images.pokemontcg.io/dp3/9_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Submission",
                damage = "60",
                text = "This attack does 10 damage to Machamp."
            )
        )
    ),
    PokemonCard(
        id = "dp3-10",
        name = "Blissey",
        nationalPokedexNumbers = listOf(113),
        types = listOf("Normal"),
        images = CardImages(
            small = "https://images.pokemontcg.io/dp3/10.png",
            large = "https://images.pokemontcg.io/dp3/10_hires.png"
        ),
        attacks = listOf(
            Attack(
                name = "Softboiled",
                damage = "0",
                text = "Heal 50 damage from one of your Pokémon."
            )
        )
    ),
)

