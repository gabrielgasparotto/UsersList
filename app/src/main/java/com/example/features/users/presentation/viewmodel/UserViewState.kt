package com.example.features.users.presentation.viewmodel

import com.example.features.users.domain.model.User

sealed class UserViewState {

    data class UserSuccess(val users: List<User>) : UserViewState()
    data class UserError(val error: Throwable) : UserViewState()
}