package com.example.chapter5.repository

import com.example.chapter5.databases.AkunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AkunRepository)
}