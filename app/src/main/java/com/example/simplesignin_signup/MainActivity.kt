package com.example.simplesignin_signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplesignin_signup.Data.UserViewModel
import com.example.simplesignin_signup.screens.HomeScreen
import com.example.simplesignin_signup.screens.SignInScreen
import com.example.simplesignin_signup.screens.SignUpScreen
import androidx.navigation.NavHostController
import com.example.simplesignin_signup.screens.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: UserViewModel = viewModel(
                factory = ViewModelProvider.AndroidViewModelFactory(application)
            )
            AppNavigation(viewModel)
        }
    }
}

@Composable
fun AppNavigation(viewModel: UserViewModel) {
    val navController = rememberNavController() // Create a NavController
    NavHost(navController = navController, startDestination = "home") {
        composable("signin") { SignInScreen(navController = navController, viewModel = viewModel) }
        composable("signup") { SignUpScreen(navController = navController, viewModel = viewModel) }
        composable("home") { HomeScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
    }
}


