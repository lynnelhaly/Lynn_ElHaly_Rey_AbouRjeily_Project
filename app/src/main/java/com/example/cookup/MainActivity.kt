package com.example.cookup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.cookup.data.viewmodel.AuthViewModel
import com.example.cookup.data.viewmodel.AuthViewModelFactory
import com.example.cookup.navigation.AppNavGraph
import com.example.cookup.ui.theme.CookUpTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as CookUpApp

        setContent {
            CookUpTheme {
                val navController = rememberNavController()

                val authViewModel: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(app.authRepository)
                )

                AppNavGraph(
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        }
    }
}
