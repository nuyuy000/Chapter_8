package com.example.chapter5.databases

import com.example.chapter5.datastore.UserDataStore
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class AkunRepositoryTest {

    private lateinit var akunDatabase: AkunDatabase
    private lateinit var userDataStoreManager: UserDataStore
    private lateinit var akunRepository: AkunRepository

    @Before
    fun setUp() {
        akunDatabase = mockk()
        userDataStoreManager = mockk()
        akunRepository = AkunRepository(akunDatabase, userDataStoreManager)
    }

    @Test
    fun login() {
        val returnLogin = mockk<Akun>()

        every {
            runBlocking {
                akunDatabase.akunDao().login("email", "password")
            }
        } returns returnLogin
        runBlocking {
            akunRepository.login("email", "password")
        }

        verify {
            runBlocking {
                akunDatabase.akunDao().login("email", "password")
            }
        }
    }
}