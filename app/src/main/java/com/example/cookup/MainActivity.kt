package com.example.cookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.cookup.navigation.AppNavGraph
import com.example.cookup.ui.theme.CookUpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CookUpTheme {
                // Initialize the NavController
                val navController = rememberNavController()

                // Pass the NavController to the AppNavGraph to manage navigation
                AppNavGraph(navController)  // This will handle the navigation flow
            }
        }
    }
}
