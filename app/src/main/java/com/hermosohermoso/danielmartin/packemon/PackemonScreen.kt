package com.hermosohermoso.danielmartin.packemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hermosohermoso.danielmartin.packemon.data.HomeScreen
import com.hermosohermoso.danielmartin.packemon.data.PackOpeningScreen
import com.hermosohermoso.danielmartin.packemon.data.PokemonPulled
import com.hermosohermoso.danielmartin.packemon.model.PackemonScreens
import com.hermosohermoso.danielmartin.packemon.ui.PokemonViewModel

@Composable
fun PackemonApp(
    windowSize: WindowWidthSizeClass,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    val viewModel: PokemonViewModel = viewModel()
    val backStackEntry by navController.currentBackStackEntryAsState()
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
            composable(route = PackemonScreens.Start.route){
                HomeScreen(navController, packemonUiState, viewModel)
            }
            composable(route = PackemonScreens.SobreAbierto.route){
                PackOpeningScreen(navController, packemonUiState, viewModel)
            }
            composable(route = PackemonScreens.PokeObtenidos.route){
                PokemonPulled(navController, viewModel, packemonUiState)
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
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(com.hermosohermoso.danielmartin.packemon.R.drawable.logopackemon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(500.dp)
                        .padding(bottom = 12.dp)
                )
            }
        },
//        colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primary,
//            titleContentColor = MaterialTheme.colorScheme.onPrimary
//        ),
        modifier = modifier,
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