package com.example.chapter5.databases

import androidx.room.*


@Dao
interface AkunDao {
    @Query("SELECT * FROM akun WHERE  username = :username AND password = :password")
    fun login(username: String, password: String):Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(akun: Akun): Long
//
//    @Query("SELECT * FROM akun WHERE email =:email")
//    fun getuser(email: String):Akun?
//
//    @Update
//    fun updateuser(user: Akun):Int
}