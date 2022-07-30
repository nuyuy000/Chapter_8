package com.example.chapter5.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class AkunDatabase : RoomDatabase() {

    abstract fun akunDao(): UserDao

}