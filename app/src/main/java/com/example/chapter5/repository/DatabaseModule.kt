package com.example.chapter5.repository

import androidx.room.Room
import com.example.chapter5.databases.AkunDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(get()
            , AkunDatabase::class.java, "akun.db").build()
    }

    single {
        get<AkunDatabase>().akunDao()
    }

}