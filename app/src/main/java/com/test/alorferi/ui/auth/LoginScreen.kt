package com.test.alorferi.ui.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.test.alorferi.R
import com.test.alorferi.routes.Screens
import com.test.alorferi.ui.components.GetMobileNoView
import com.test.alorferi.ui.components.GetPasswordView
import com.test.alorferi.ui.theme.AlorFeriTheme
import com.test.alorferi.ui.theme.DeepRed500
import com.test.alorferi.ui.theme.DeepRed700

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val userMobileText by viewModel.userMobileText.collectAsState()
    val userPasswordText by viewModel.userPasswordText.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    //val loginResponse by viewModel.loginResponse.collectAsState()

//    LaunchedEffect(loginResponse) {
//        if (loginResponse != null) {
//            Log.d("response", loginResponse.toString())
////            navigateToHome()
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .pointerInput(Unit) {
                // Clear focus when the screen is touched without showing visual effects
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .verticalScroll(scrollState) // Add vertical scrolling
            .systemBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

         //Logo and Title
        Image(
            painter = painterResource(id = R.drawable.alorferi_logo), // Replace with your logo
            contentDescription = "App Logo",
            modifier = Modifier.size(120.dp)
        )
        Text(
            text = "Library Management System",
            fontSize = 14.sp,
            color = DeepRed700,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Email or Mobile TextField
        GetMobileNoView(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            mobileText = userMobileText,
            onTextChanged = { newMobileText -> viewModel.onPhoneNoChange(newMobileText) },
            hint = "Email or Mobile No"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField with Eye Icon
        GetPasswordView(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            passwordText = userPasswordText,
            onTextChanged = { newText -> viewModel.onPasswordChange(newText) },
            hint = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                viewModel.onCLickLogin(context)
                navController.navigate(Screens.ProfileScreen)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)) // Orange color
        ) {
            Text(text = "Log In", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Forgot Password
        Text(
            text = "Forgot Password?",
            color = DeepRed500,
            modifier = Modifier.clickable { /* Handle forgot password */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Create New Account Button
        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)) // Green color
        ) {
            Text(text = "Create New Account", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Footer
        Text(
            text = "Copyright © Alor Feri Limited",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Text(
            text = "Version: 0.0.0",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}