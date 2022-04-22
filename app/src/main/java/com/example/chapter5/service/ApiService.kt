package com.example.chapter5.service

import com.example.chapter5.databases.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/movie/popular")
    fun getpopular():Call<MoviePopular>
//
//    @GET("/movie/{movie_id}")
//    fun getdetail(@Path("id")id:Int):Call<Detail>
//
//    @POST("admin/auth/login")
//    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>
}