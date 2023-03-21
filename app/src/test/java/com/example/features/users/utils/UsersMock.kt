package com.example.features.users.utils

import com.example.features.users.data.source.remote.model.UserResponse
import com.example.features.users.domain.model.User

object UserMock {

    fun mockUserResponse() = listOf(
        UserResponse(
            "gaspa",
            1,
            "teste",
            "teste.com",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            "teste",
            false
        )
    )

    fun mockUser() = listOf(
        User(
            1,
            "teste.com",
            "teste",
            "teste"
        )
    )

    fun mockUserSuccess(): Result<List<User>> = Result.success(mockUser())
    fun mockUserFailure(exception: Throwable): Result<List<User>> =
        Result.failure(exception)
}