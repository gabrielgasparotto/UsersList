package com.example.features.users.domain.usecase

import com.example.features.users.domain.model.User
import com.example.features.users.domain.repository.UserRepository

class UserUseCase(private val userRepository: UserRepository) {

    suspend fun getUsers(): Result<List<User>>{
        return try {
            userRepository.getUsers()
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}