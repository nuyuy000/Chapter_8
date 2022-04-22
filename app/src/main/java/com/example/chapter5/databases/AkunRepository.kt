package com.example.chapter5.databases

import android.content.Context
import kotlinx.coroutines.coroutineScope

class AkunRepository (context: Context) {
    val DbAkun = AkunDatabase.getInstance(context)
    suspend fun login(username: String, password: String): Boolean? = coroutineScope {
        return@coroutineScope DbAkun?.akunDao()?.login(username, password)
    }

    suspend fun addUser(akun: Akun): Long? = coroutineScope {
        return@coroutineScope DbAkun?.akunDao()?.addUser(akun)
    }
//
//    //
//    suspend fun update(user: Akun): Int? = coroutineScope {
//        return@coroutineScope DbAkun?.akunDao()?.updateuser(user)
//    }
////
//    suspend fun getuser(email: String): Akun? = coroutineScope {
//        return@coroutineScope DbAkun?.akunDao()?.getuser(email)
//
//    }
}