package com.example.simplesignin_signup.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_data_table")
data class User(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo("user_id")
    val id: Int = 0,

    @ColumnInfo("email")
    val email: String,

    @ColumnInfo("password")
    val password: String
)