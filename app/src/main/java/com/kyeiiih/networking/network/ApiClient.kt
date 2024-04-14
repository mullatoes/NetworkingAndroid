package com.kyeiiih.networking.network

import com.kyeiiih.networking.model.User
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

}

sealed class UserUiState {
    object Loading : UserUiState()
    data class Success(val user: User) : UserUiState()
    object Error : UserUiState()
}

sealed class AllUsersUiState {
    object Loading : AllUsersUiState()
    data class Success(val user: List<User>) : AllUsersUiState()
    object Error : AllUsersUiState()
}