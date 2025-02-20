package com.test.alorferi.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.alorferi.R
import com.test.alorferi.routes.Screens
import com.test.alorferi.ui.components.CustomTopBar
import com.test.alorferi.ui.theme.OrangeLight
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val hasNavigated by viewModel.hasNavigated.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
             .systemBarsPadding(),
    ) {

        CustomTopBar{
                navController.navigate(Screens.ProfileScreen)
            }


         Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


        }
    }
    // Navigate only after loading is complete
//    LaunchedEffect(key1 = loading, key2 = isLoggedIn, key3 = hasNavigated) {
//        if (!loading && !hasNavigated) {
//            if (isLoggedIn) {
//                // Navigate to HomeScreen if logged in
//                navController.navigate(route = Screens.HomeScreen) {
//                    popUpTo(Screens.LoginScreen) { inclusive = true }
//                }
//            } else {
//                // Navigate to LoginScreen if not logged in
//                navController.navigate(route = Screens.LoginScreen) {
//                    popUpTo(Screens.HomeScreen) { inclusive = true }
//                }
//            }
//            viewModel.markNavigated()
//        }
//    }

    // Show a loading indicator while waiting for DataStore
//    if (loading) {
//        LoadingScreen()
//    } else {
//        Column {
//            Text(
//                text = "Welcome to Alor Feri!",
//                style = MaterialTheme.typography.labelMedium
//            )
//        }
//    }
}

@Composable
fun LoadingScreen() {
    Column {
        Text(text = "Loading...", style = MaterialTheme.typography.labelMedium)
    }
}




@Composable
fun HomeContent(navController: NavController) {
    Column(
    ) {
        Text(text = "Welcome to the Home Screen!", style = MaterialTheme.typography.labelMedium)
    }
}
