package com.example.simplesignin_signup.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.simplesignin_signup.Data.UserViewModel

var inputEmail: String = ""
var inputPassword: String = ""

@Composable
fun SignInScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel
) {
    val inputEmail = viewModel.inputEmail
    val inputPassword = viewModel.inputPassword
    val context = LocalContext.current
    val status = viewModel.status

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 280.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 5.dp)
        ){
            OutlinedTextField(
                value = inputEmail,
                onValueChange = { text ->
                    viewModel.onEmailChange(text)
                },
                label = {
                    Text(
                        "Email Address",
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
        ){
            OutlinedTextField(
                value = inputPassword,
                onValueChange = { text ->
                    viewModel.onPasswordChange(text)
                },
                label = {
                    Text(
                        "Password",
                        fontSize = 20.sp
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
        Row(

        ){
            Button(onClick = {
                if (inputEmail.isNotBlank() && inputPassword.isNotBlank()) {
                    viewModel.login()
                }else {
                    Toast.makeText(context, "Please enter your email & password", Toast.LENGTH_SHORT).show()
                }

            },
                modifier = Modifier
                    .width(255.dp)
                    .padding(start = 110.dp)
                    .padding(bottom = 10.dp)
            ) {
                Text(
                    "Sign In",
                    fontSize = 21.sp
                )
            }

            // Show Toast based on status change
            LaunchedEffect(status) {
                when (status) {
                    "Login successful" -> {
                        navController.navigate("welcome")
                    }
                    "Invalid email or password" -> {
                        Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        Row(

        ){
            Text(
                "No account? Create one",
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline, // Make it look like a hyperlink
                modifier = Modifier
                    .padding(start = 70.dp)
                    .clickable {
                        navController.navigate("signup") // Navigate to the details screen
                    }
            )
        }
    }
}