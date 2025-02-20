package com.test.alorferi.ui.profile

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.test.alorferi.R
import com.test.alorferi.ui.components.BackIconButton
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userResponse by profileViewModel.userResponseFlow.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding(),
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        )
        {
            BackIconButton(
                onClick = {
                    navController.navigateUp()
                }
            )
        }
        // Profile Picture Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box {
                userResponse?.data?.attributes?.photo_url?.let { photoUrl ->
                    Log.d("ImageURL", "https://backoffice.alorferi.com$photoUrl")
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://backoffice.alorferi.com$photoUrl") // Build full URL dynamically
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.blank_profile),
                        error = painterResource(R.drawable.blank_profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_photo_camera), // Replace with your camera icon
                    contentDescription = "Edit Profile Picture",
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(1.dp, Color.Gray, CircleShape)
                        .padding(4.dp)
                )
            }
        }

        // User Name
        userResponse?.data?.attributes?.first_name?.let { firstName ->
            val lastName = userResponse?.data?.attributes?.surname ?: "" // Handle null last name
            Text(
                text = "$firstName $lastName",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
            )
        }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // For naming row
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 4.dp // Set elevation here
                    ),
                    shape = RoundedCornerShape(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 7.dp, end = 7.dp)
                            .padding(vertical = 4.dp), // Add spacing between rows
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Name",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f)
                        )
                        userResponse?.data?.attributes?.first_name?.let { firstName ->
                            val lastName = userResponse?.data?.attributes?.surname ?: "" // Handle null last name
                            Text(
                                text = "$firstName $lastName", // Concatenate first name and last name
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.End
                            )
                        }

                    }
                }

                // For Email Row
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(5.dp)
                        .clickable {
                            // Navigate to the new activity
                            val intent = Intent(context, EditProfileActivity::class.java)
                            context.startActivity(intent)
                        },
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 4.dp // Set elevation here
                    ),
                    shape = RoundedCornerShape(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 7.dp, end = 7.dp)
                            .padding(vertical = 4.dp), // Add spacing between rows
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "E-mail",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f)
                        )
                        userResponse?.data?.attributes?.email?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.End // Align the value to the end of the row
                            )
                        }
                    }
                }

                // For DOB Row
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(5.dp),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = 4.dp // Set elevation here
                    ),
                    shape = RoundedCornerShape(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 7.dp, end = 7.dp)
                            .padding(vertical = 4.dp), // Add spacing between rows
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Date of birth",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.weight(1f)
                        )
                        userResponse?.data?.attributes?.dob?.let { dob ->
                            // Parse the date and format it
                            val formattedDob = ZonedDateTime.parse(dob)
                                .toLocalDate() // Extract the local date part
                                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) // Format the date

                            Text(
                                text = formattedDob, // Display the formatted date
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                                modifier = Modifier.weight(2f),
                                textAlign = TextAlign.End // Align the value to the end of the row
                            )
                        }
                    }
                }
//                ProfileInfoRow(label = "Name", value = "Prianka Akter")
//                ProfileInfoRow(label = "E-mail", value = "priankasarker966@gmail.com")
//                ProfileInfoRow(label = "Date of birth", value = "31 Dec 2000")
            }

    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    // User Info Section in Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4.dp // Set elevation here
        ),
        shape = RoundedCornerShape(3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 7.dp, end = 7.dp)
                .padding(vertical = 4.dp), // Add spacing between rows
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue),
                modifier = Modifier.weight(2f),
                textAlign = TextAlign.End // Align the value to the end of the row
            )
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun AlorFeriPreview() {
//    AlorFeriTheme {
//        ProfileScreen(navController = rememberNavController())
//    }
//}