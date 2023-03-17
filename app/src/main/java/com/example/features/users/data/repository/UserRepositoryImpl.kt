package com.example.features.users.data.repository

import com.example.features.users.data.mapper.UserMapper
import com.example.features.users.data.source.remote.data.UserRemoteData
import com.example.features.users.domain.model.User
import com.example.features.users.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val mapper: UserMapper,
    private val remoteData: UserRemoteData
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return getUserRemote()
    }

    private suspend fun getUserRemote(): Result<List<User>> = withContext(Dispatchers.IO) {
        try {
            Result.success(mapper.map(remoteData.getUsers()))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}