package com.kyeiiih.networking.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyeiiih.networking.network.AllUsersUiState
import com.kyeiiih.networking.network.UserService
import com.kyeiiih.networking.network.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserViewModel(private val userService: UserService) : ViewModel() {

    private val _userUiState = MutableLiveData<UserUiState>()
    val userUiState: LiveData<UserUiState> get() = _userUiState

    private val _allUsersUiState = MutableLiveData<AllUsersUiState>()
    val allUsersUiState: LiveData<AllUsersUiState> get() = _allUsersUiState

    fun getUserById(id: Int) {
        viewModelScope.launch {
            _userUiState.value = UserUiState.Loading
            try {
                val userById = userService.getUserById(id)
                println("User received: $userById")
                _userUiState.value = UserUiState.Success(userById)
            } catch (e: Exception) {
                println("Error fetching user: ${e.message}")
                _userUiState.value = UserUiState.Error
            }
        }
    }

    fun getUsers(page: Int){
        viewModelScope.launch {
            _allUsersUiState.value = AllUsersUiState.Loading
            try {
                val fetchedUsers = userService.getUsers(page)
                println("User Size: ${fetchedUsers.size}")
                _allUsersUiState.value = AllUsersUiState.Success(fetchedUsers)
            } catch (e: Exception) {
                println("Error fetching user: ${e.message}")
                _allUsersUiState.value = AllUsersUiState.Error
            }
        }
    }
}
