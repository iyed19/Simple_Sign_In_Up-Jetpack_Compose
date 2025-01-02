package com.example.simplesignin_signup.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT COUNT(*) FROM users_data_table WHERE email = :email")
    suspend fun isEmailRegistered(email: String): Int

    @Query("SELECT * FROM users_data_table WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users_data_table")
    fun getAllUsers(): List<User>
}