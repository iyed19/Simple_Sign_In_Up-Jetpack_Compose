package com.example.simplesignin_signup.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserRoomDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}