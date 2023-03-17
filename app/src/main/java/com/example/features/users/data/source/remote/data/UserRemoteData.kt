package com.example.features.users.data.source.remote.data

import com.example.features.users.data.source.remote.model.UserResponse

interface UserRemoteData {
    suspend fun getUsers(): List<UserResponse>
}