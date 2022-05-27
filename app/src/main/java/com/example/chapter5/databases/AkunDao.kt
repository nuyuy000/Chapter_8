package com.example.chapter5.databases

import androidx.room.*


@Dao
interface AkunDao {
    @Query("SELECT * FROM akun WHERE  username = :username AND password = :password")
    fun login(username: String, password: String):Akun?

    @Query("SELECT * FROM Akun WHERE username = :email")
    fun checkEmailExist(email: String):Akun?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(akun: Akun):Long

    @Query("SELECT * FROM Akun WHERE username = :email")
    fun getUser(email: String): Akun?

    @Update
    fun updatetUser(akun: Akun):Int
//
//    @Query("SELECT * FROM akun WHERE email =:email")
//    fun getuser(email: String):Akun?
//
//    @Update
//    fun updateuser(user: Akun):Int
}