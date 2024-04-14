package com.kyeiiih.networking.network

import com.kyeiiih.networking.model.User
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int) : List<User>
}