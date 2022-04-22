package com.example.chapter5.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter5.activity.Detail
import com.example.chapter5.databases.MoviePopular
import com.example.chapter5.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private val _dataMovie: MutableLiveData<MoviePopular> by lazy {
        MutableLiveData<MoviePopular>().also {
            getpopular()
        }
    }
    val dataMovie: LiveData<MoviePopular> = _dataMovie

    //mengambil data movie
    private fun getpopular(){
        ApiClient.instance.getpopular().enqueue(object : Callback<MoviePopular> {
            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                if (response.code() == 200){
                    _dataMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
            }
        })
    }
    val detailMovie: MutableLiveData<Detail> = MutableLiveData()
    //mengambil data detail
    fun getdetail(id:Int){
        ApiClient.instance.getdetail(id).enqueue(object : Callback<Detail> {
            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                if (response.code() == 200){
                    detailMovie.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Detail>, t: Throwable) {
            }
        })
    }
}