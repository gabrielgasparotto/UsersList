package com.example.features.users.domain.repository

import com.example.features.users.domain.model.User

interface UserRepository {
    suspend fun getUsers(): Result<List<User>>
}