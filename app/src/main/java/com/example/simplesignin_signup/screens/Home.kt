package com.example.simplesignin_signup.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize(), // Make the Box fill the entire screen
        contentAlignment = Alignment.Center // Center content inside the Box
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // Center items horizontally
            verticalArrangement = Arrangement.Center, // Space between buttons
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    navController.navigate("signin")
                },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(start = 100.dp)
                        .padding(bottom = 10.dp)
                ) {
                    Text(
                        "Sign In",
                        fontSize = 21.sp

                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    navController.navigate("signup")
                },
                    modifier = Modifier
                        .width(300.dp)
                        .padding(start = 100.dp)
                ) {
                    Text(
                        "Sign Up",
                        fontSize = 21.sp
                    )
                }
            }
        }
    }

}