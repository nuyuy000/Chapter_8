package com.example.chapter5.databases

import com.google.gson.annotations.SerializedName
import kotlin.Result

class DataMovieResponse (

    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<com.example.chapter5.databases.Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
        )
