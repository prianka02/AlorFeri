package com.test.alorferi.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.alorferi.ui.auth.LoginScreen
import com.test.alorferi.ui.home.HomeScreen
import com.test.alorferi.ui.profile.ProfileScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen) {
        composable<Screens.HomeScreen> {
            HomeScreen(navController)
        }
        composable<Screens.LoginScreen> {
            LoginScreen(navController)
        }
        composable<Screens.ProfileScreen> {
            ProfileScreen(navController)
        }
    }
}