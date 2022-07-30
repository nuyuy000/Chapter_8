package com.example.chapter5.activity

import androidx.lifecycle.*
import com.example.chapter5.databases.AkunRepository
import com.example.chapter5.databases.User
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AkunRepository): ViewModel() {

    private val _user: MutableLiveData<User?>  = MutableLiveData()
    val user: LiveData<User?> = _user

    fun getUser(email: String){
        viewModelScope.launch {
            val newUser = repository.getUser(email)
            _user.postValue(newUser)
        }
    }

    fun updateUser(user: User) = repository.updateUser(user)


    fun login(email: String, password: String) = repository.login(email, password)

    fun register(user: User) = repository.register(user)

    fun checkIfEmailExist(email: String) = repository.checkEmailIfExist(email)



    //login preference
    fun setEmailPreference(email: String){
        viewModelScope.launch {
            repository.setEmail(email)
        }
    }

    fun setNamaPreference(nama: String){
        viewModelScope.launch {
            repository.setNama(nama)
        }
    }

    fun deletePref() = viewModelScope.launch {
        repository.deletePref()
    }

    fun emailPreference() = repository.getEmail()




}



