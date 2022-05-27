package com.example.chapter5.repository

import com.example.chapter5.databases.Akun
import com.example.chapter5.databases.AkunDao

class AuthRepo (private val akunDao: AkunDao) {

    fun login(email: String, password: String):Akun? = akunDao.login(email, password)
    fun register(user: Akun):Long = akunDao.insertUser(user)
    fun checkEmailIfExist(email: String): Akun? = akunDao.checkEmailExist(email)
    fun getUser(email: String): Akun? = akunDao.getUser(email)
    fun updateUser(user: Akun):Int = akunDao.updatetUser(user)
}