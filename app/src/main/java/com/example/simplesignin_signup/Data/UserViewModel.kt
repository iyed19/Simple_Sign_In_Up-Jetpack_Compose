package com.example.simplesignin_signup.Data

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val database = Room.databaseBuilder(
        application.applicationContext,
        UserRoomDB::class.java,
        "users_DB"
    ).build()

    private val userDao = database.userDao()

    var inputEmail by mutableStateOf("")
        private set

    var inputPassword by mutableStateOf("")
        private set

    var status by mutableStateOf("")
        private set

    var isEmailValid by  mutableStateOf(true)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userDao.getAllUsers()
            // ListofUsers.addAll(users)
        }
    }

    fun onEmailChange(newEmail: String) {
        inputEmail = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        inputPassword = newPassword
    }


    fun addUser() {
        val email = inputEmail
        val password = inputPassword

        viewModelScope.launch(Dispatchers.IO) {
            isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
            if (isEmailValid) {
                if (userDao.isEmailRegistered(email) > 0) {
                    updateStatus("Email already registered")
                }else{
                    val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
                    //Log.d("psw", "Hashed Password: $hashedPassword")
                    val user = User(email = email, password = hashedPassword)
                    userDao.insertUser(user)
                    //updateStatus("Account created successfully")
                }
            } else{
                updateStatus("Email is not valid")
            }
            clearFields()
        }
    }

    fun login(){
        val email = inputEmail
        val password = inputPassword

        viewModelScope.launch(Dispatchers.IO) {
            val user = userDao.getUserByEmail(email)
            if (user != null && BCrypt.checkpw(password, user.password)) {
                viewModelScope.launch(Dispatchers.Main) {
                    updateStatus("Login successful")
                }
            } else {
                viewModelScope.launch(Dispatchers.Main) {
                    updateStatus("Invalid email or password")
                }
            }
            clearFields()
        }
    }



    private fun updateStatus(newStatus: String) {
        viewModelScope.launch(Dispatchers.Main) {
            status = newStatus
        }
    }

    fun clearFields(){
        inputEmail = ""
        inputPassword = ""
        status = ""
    }
}