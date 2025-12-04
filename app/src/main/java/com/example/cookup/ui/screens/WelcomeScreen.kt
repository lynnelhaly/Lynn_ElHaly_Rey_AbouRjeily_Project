package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape // Import this for RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cookup.R // Import this for drawable resource
import com.example.cookup.navigation.NavRoutes

@Composable
fun WelcomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    // Soft gradient background with a smoother transition
    val gradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE3F2FD),  // Light Blue
            Color(0xFFEFF2F6)   // Off White
        )
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(gradient)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // App Logo with a circular background
            Surface(
                modifier = Modifier.size(160.dp),  // Increased logo size
                shape = CircleShape,  // Circular background
                color = Color.White.copy(alpha = 0.85f),
                shadowElevation = 15.dp  // Slightly stronger shadow
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.cookuplogo),
                        contentDescription = "App Logo",
                        modifier = Modifier.size(120.dp)  // Further increase logo size for prominence
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "CookUp",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2A2A2A)  // Dark Gray for contrast
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Find recipes from what you already have",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF4A4A4A).copy(alpha = 0.75f)
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Primary Button with rounded corners
            Button(
                onClick = {
                    navController.navigate(NavRoutes.INGREDIENTS)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))  // Accent color fixed here
            ) {
                Text("Get Started", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Secondary Button (text only)
            TextButton(
                onClick = {
                    navController.navigate(NavRoutes.RECIPES + "/all")
                }
            ) {
                Text("Browse Recipes",
                    color = Color(0xFF6A6A6A)
                )
            }
        }
    }
}


