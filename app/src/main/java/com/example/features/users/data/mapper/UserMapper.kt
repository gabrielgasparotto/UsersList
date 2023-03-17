package com.example.features.users.data.mapper

import com.example.features.users.data.source.remote.model.UserResponse
import com.example.features.users.domain.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserMapper {

    suspend fun map(reponse: List<UserResponse>): List<User>{
        return withContext(Dispatchers.Default) {
            reponse.map { source ->
                User(
                    id = source.id,
                    login = source.login,
                    type = source.type,
                    illustration = source.avatar
                )
            }
        }
    }
}