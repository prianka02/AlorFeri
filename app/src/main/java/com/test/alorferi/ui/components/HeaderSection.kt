package com.test.alorferi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.test.alorferi.R
import com.test.alorferi.ui.theme.AlorFeriTheme
import com.test.alorferi.ui.theme.OrangeDark
import com.test.alorferi.ui.theme.OrangeLight
import com.test.alorferi.ui.theme.OrangePrimary

@Composable
fun CustomTopBar(
    onClick: () -> Unit
) {
    Box(
        Modifier.fillMaxWidth()
            .height(60.dp)
            .background(OrangePrimary),
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Icon
            Box(
                contentAlignment = Alignment.Center
            ){
                Image(
                    painter = painterResource(R.drawable.blank_profile), // Replace with your image resource
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .clickable( onClick = onClick )
//                    .border(2.dp, Color.Gray, CircleShape)
                )

            }
            Text(
                text = "আলোর ফেরী",
                color = Color.White,
                modifier = Modifier
                    .padding(start = 1.dp, end = 150.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge
            )



            // Cart Icon
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Cart",
                tint = Color.White,
                modifier = Modifier.size(35.dp),
            )
        }
    }
}
