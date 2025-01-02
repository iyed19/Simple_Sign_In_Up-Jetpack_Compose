package com.example.simplesignin_signup.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
    ) {
    Column(

    ) {
        Row(

        ) {
            Text(
                "Welcome to our App",
                fontSize = 33.sp,
                modifier = Modifier
                    .padding(start = 50.dp)
                    .padding(top = 390.dp)
            )
        }
    }
}