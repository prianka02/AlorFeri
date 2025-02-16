package com.test.alorferi.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.alorferi.routes.Screens

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    LaunchedEffect(isLoggedIn) {
        if (!isLoggedIn) {
            navController.navigate(Screens.LoginScreen) {
                popUpTo(Screens.HomeScreen) { inclusive = true }
            }
        }
    }

    if (isLoggedIn) {
        // Render home content
        HomeContent(navController)
    }
}

@Composable
fun HomeContent(navController: NavController) {
    Column(
    ) {
        Text(text = "Welcome to the Home Screen!", style = MaterialTheme.typography.labelMedium)
    }
}
