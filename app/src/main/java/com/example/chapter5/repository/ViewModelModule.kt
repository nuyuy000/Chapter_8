package com.example.chapter5.repository

import com.example.chapter5.model.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MovieViewModel)
}