package com.hermosohermoso.danielmartin.packemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hermosohermoso.danielmartin.packemon.bbdd.PackemonBbddViewModel
import com.hermosohermoso.danielmartin.packemon.data.vertical.HomeScreen
import com.hermosohermoso.danielmartin.packemon.data.apaisado.HomeScreenApaisado
import com.hermosohermoso.danielmartin.packemon.data.apaisado.PackOpenedApaisado
import com.hermosohermoso.danielmartin.packemon.data.apaisado.PackOpeningScreenApaisado
import com.hermosohermoso.danielmartin.packemon.data.apaisado.PokedexApaisado
import com.hermosohermoso.danielmartin.packemon.data.apaisado.PokemonDatosScreenApaisado
import com.hermosohermoso.danielmartin.packemon.data.apaisado.PokemonPulledApaisado
import com.hermosohermoso.danielmartin.packemon.data.vertical.PackOpened
import com.hermosohermoso.danielmartin.packemon.data.vertical.PackOpeningScreen
import com.hermosohermoso.danielmartin.packemon.data.vertical.Pokedex
import com.hermosohermoso.danielmartin.packemon.data.vertical.PokemonDatosScreen
import com.hermosohermoso.danielmartin.packemon.data.vertical.PokemonPulled
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.stringResource

@Composable
fun PackemonApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
    bbddViewModel: PackemonBbddViewModel = viewModel(factory = PackemonBbddViewModel.factory),
    viewModel: PokemonViewModel = viewModel(factory = PokemonViewModel.factory),
    modifier: Modifier = Modifier
){
    val packemonUiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            PokeAppTopBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ){ paddingValues ->
        NavHost(
            navController = navController,
            startDestination = PackemonScreens.Start.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            if (windowSize == WindowWidthSizeClass.Expanded) {
                composable(route = PackemonScreens.Start.route){
                    HomeScreenApaisado(navController, packemonUiState, viewModel)
                } // Inicio
                composable(route = PackemonScreens.SobreAbierto.route){
                    PackOpeningScreenApaisado(navController, packemonUiState, viewModel)
                } // Sobre abierto
                composable(route = PackemonScreens.PokeObtenidos.route){
                    PokemonPulledApaisado(navController, viewModel, packemonUiState, bbddViewModel)
                } // Pokemon Obtenido
                composable(route = PackemonScreens.SobreAbiertoCompleto.route){
                    PackOpenedApaisado(navController, viewModel , packemonUiState)
                } // Sobre abierto completo
                composable(route = PackemonScreens.Pokedex.route){
                    PokedexApaisado(navController, viewModel, bbddViewModel, packemonUiState)
                } // Pokedex
                composable(route = PackemonScreens.PokemonDatos.route){
                    PokemonDatosScreenApaisado(navController, viewModel, bbddViewModel)
                } // Vista pokemon
            }else {
                composable(route = PackemonScreens.Start.route){
                    HomeScreen(navController, packemonUiState, viewModel)
                } // Inicio
                composable(route = PackemonScreens.SobreAbierto.route){
                    PackOpeningScreen(navController, packemonUiState, viewModel)
                } // Sobre abierto
                composable(route = PackemonScreens.PokeObtenidos.route){
                    PokemonPulled(navController, viewModel, packemonUiState, bbddViewModel)
                } // Pokemon Obtenido
                composable(route = PackemonScreens.SobreAbiertoCompleto.route){
                    PackOpened(navController, viewModel , packemonUiState)
                } // Sobre abierto completo
                composable(route = PackemonScreens.Pokedex.route){
                    Pokedex(navController, viewModel, bbddViewModel, packemonUiState)
                } // Pokedex
                composable(route = PackemonScreens.PokemonDatos.route){
                    PokemonDatosScreen(navController, viewModel, bbddViewModel)
                } // Vista pokemon
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokeAppTopBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,

            ) {
                Image(
                    painter = painterResource(R.drawable.logopackemon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(500.dp)
                        .padding(bottom = 12.dp, top = 8.dp)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

//Esta función sera usada desde diferentes pantallas por lo que la dejaré aqui
fun mostrarImgFav(estado: Boolean): Int{
    if (estado == true) {
        return R.drawable.favoritomarcado
    } else {
        return R.drawable.favorito
    }
}