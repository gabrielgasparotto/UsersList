package com.example.features.users.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.features.users.domain.usecase.UserUseCase
import com.example.features.users.utils.CoroutineTestRule
import com.example.features.users.utils.UserMock.mockUser
import com.example.features.users.utils.UserMock.mockUserFailure
import com.example.features.users.utils.UserMock.mockUserSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UserViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val scope = CoroutineTestRule()

    @Test
    fun `UserViewStateSucess ao init viewmodel com getUsers ok e com lista preenchida`() {
        val useCase: UserUseCase = mockk()
        coEvery { useCase.getUsers() } returns mockUserSuccess()
        val viewModel = UserViewModel(useCase)
        runBlocking {
            viewModel.getUsers()
            assertEquals(UserViewState.UserSuccess(mockUser()), viewModel.state.value)
        }
    }

    @Test
    fun `UserViewStateEmptyError ao init viewmodel com getUsers ok e com lista vazia`() {
        val useCase: UserUseCase = mockk()
        val exception = Exception()
        coEvery { useCase.getUsers() } returns mockUserFailure(exception)
        val viewModel = UserViewModel(useCase)
        runBlocking {
            viewModel.getUsers()
            assertEquals(UserViewState.UserError(exception), viewModel.state.value)
        }
    }

    @Test
    fun `UserError ao init viewmodel com getUsers exception desconhecida`() {
        val useCase: UserUseCase = mockk()
        val exception = NullPointerException("mock")
        coEvery { useCase.getUsers() } returns mockUserFailure(exception)
        val viewModel = UserViewModel(useCase)
        runBlocking {
            viewModel.getUsers()
            assertEquals(UserViewState.UserError(exception), viewModel.state.value)
        }
    }
}