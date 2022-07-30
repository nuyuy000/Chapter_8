package com.example.chapter5.databases

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    fun login(email: String, password: String): User?

    @Query("SELECT * FROM User WHERE email = :email")
    fun checkEmailExist(email: String):User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User):Long

    @Query("SELECT * FROM User WHERE email = :email")
    suspend fun getUser(email: String): User?

    @Update
    fun updatetUser(user: User):Int

    @Query("UPDATE User SET avatarPath=:avatarPath WHERE id=:id")
    suspend fun updateAvatarPath(id: Int,avatarPath: String):Int

}