package com.example.chapter5.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Akun::class], version = 1)
abstract class AkunDatabase : RoomDatabase() {

    abstract fun akunDao(): AkunDao

    companion object {

        @Volatile
        private var INSTANCE: AkunDatabase? = null
        fun getInstance(context: Context): AkunDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AkunDatabase::class.java, "akun.db"
                ).build()

            }
            return INSTANCE

        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}