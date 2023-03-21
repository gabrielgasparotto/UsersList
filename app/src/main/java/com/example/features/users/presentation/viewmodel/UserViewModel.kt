package com.example.features.users.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.features.users.domain.model.User
import com.example.features.users.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class UserViewModel(private val useCase: UserUseCase) : ViewModel() {

    private val viewState: MutableLiveData<UserViewState> = MutableLiveData()
    val state: LiveData<UserViewState> = viewState

    fun getUsers() {
        viewModelScope.launch {
            interpret(useCase.getUsers())
        }
    }

    private fun interpret(result: Result<List<User>>) {
        if (result.isSuccess) {
            result.getOrNull()?.let {
                viewState.postValue(UserViewState.UserSuccess(it))
            }
        } else {
            result.exceptionOrNull()?.let {
                viewState.postValue(UserViewState.UserError(it))
            }
        }
    }
}