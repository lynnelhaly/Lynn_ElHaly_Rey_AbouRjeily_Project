package com.example.cookup.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cookup.R
import com.example.cookup.navigation.NavRoutes

@Composable
fun AboutUsScreen(
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()) {

        // 🍽 Background illustration
        Image(
            painter = painterResource(id = R.drawable.welcome_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // 🧊 Soft overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.65f))
        )

        // 🤍 Center Card
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
                .shadow(18.dp, RoundedCornerShape(28.dp)),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 28.dp, vertical = 36.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🍳 Title (same as Welcome)
                Text(
                    text = "About CookUp",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF6B4E3D)
                )

                Text(
                    text = "CookUp was created for anyone who has ever opened their fridge, looked inside, and thought:",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF7A6A5A)
                )

                Text(
                    text = "“What can I even cook with this?”",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF6B4E3D)
                )

                Text(
                    text = "Instead of wasting food or time, CookUp helps you turn the ingredients you already have into real, delicious recipe ideas — quickly and easily.",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF7A6A5A)
                )

                Text(
                    text = "Built as a university project using Kotlin, Jetpack Compose, MVVM architecture, and Firebase, CookUp follows modern Android development practices while focusing on simplicity, usefulness, and a great user experience.",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color(0xFF7A6A5A),
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 🔥 Button color = Welcome screen
                Button(
                    onClick = {
                        navController.navigate(NavRoutes.WelcomeScreen.route) {
                            popUpTo(NavRoutes.WelcomeScreen.route)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE07A3F),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Back to Home",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
