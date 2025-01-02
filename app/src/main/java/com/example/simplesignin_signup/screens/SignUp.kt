package com.example.simplesignin_signup.screens

import android.annotation.SuppressLint
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


@SuppressLint("SuspiciousIndentation")
@Composable
fun SignUpScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: UserViewModel
) {
    val inputEmail = viewModel.inputEmail
    val inputPassword = viewModel.inputPassword
    val status = viewModel.status
    val context = LocalContext.current

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
                    viewModel.addUser()
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
                    "Sign Up",
                    fontSize = 21.sp
                )
            }

            // Show Toast based on status change
            LaunchedEffect(status) {
                when (status) {
//                    "Account created successfully" -> {
//                        Toast.makeText(context, "Your account created successfully", Toast.LENGTH_SHORT).show()
//                    }
                    "Email is not valid" -> {
                        Toast.makeText(context, "Please enter a valid email", Toast.LENGTH_SHORT).show()
                    }
                    "Email already registered" -> {
                        Toast.makeText(context, "This email is already used", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        Row(

        ){
            Text(
                "i already have an account",
                fontSize = 20.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(start = 65.dp)
                    .clickable {
                    navController.navigate("signin")
                }
            )
        }
    }
}