package com.example.features.users.data.source.remote.service

import com.example.features.users.data.source.remote.data.UserRemoteData
import com.example.features.users.data.source.remote.model.UserResponse
import retrofit2.http.GET

interface UserRemoteService : UserRemoteData {
    @GET("users")
    override suspend fun getUsers(): List<UserResponse>
}