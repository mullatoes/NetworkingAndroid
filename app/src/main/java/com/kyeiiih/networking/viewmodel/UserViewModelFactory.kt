package com.kyeiiih.networking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kyeiiih.networking.network.UserService

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val userService: UserService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
