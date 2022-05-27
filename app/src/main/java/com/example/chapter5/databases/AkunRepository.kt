package com.example.chapter5.databases

import android.content.Context
import androidx.lifecycle.asLiveData
import com.example.chapter5.datastore.UserDataStore
import kotlinx.coroutines.coroutineScope

class AkunRepository (private val DbAkun: AkunDatabase, private val userPref: UserDataStore) {


    suspend fun login(username: String, password: String): Akun? = coroutineScope {
        return@coroutineScope DbAkun.akunDao().login(username, password)
    }

    suspend fun addUser(akun: Akun): Long = coroutineScope {
        return@coroutineScope DbAkun.akunDao().insertUser(akun)
    }

    suspend fun setEmailPreference(email: String){
        coroutineScope { userPref.setEmail(email) }
    }

    suspend fun setNamaPreference(nama: String){
        coroutineScope { userPref.setNama(nama) }
    }
    suspend fun deletePref() = coroutineScope { userPref.deletePref() }

    fun emailPreferences () = userPref.getEmail.asLiveData()

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