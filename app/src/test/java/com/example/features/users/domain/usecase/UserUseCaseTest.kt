package com.example.features.users.domain.usecase

import com.example.features.users.domain.repository.UserRepository
import com.example.features.users.utils.UserMock
import com.example.features.users.utils.UserMock.mockUserSuccess
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserUseCaseTest {

    @Test
    fun `getUsers com repository ok deve retornar sucesso`() {
        runBlocking {
            //prepare
            val repository: UserRepository = mockk()
            val useCase = UserUseCase(repository)
            coEvery { repository.getUsers() } returns mockUserSuccess()

            //execute
            val result = useCase.getUsers()

            //validate
            assertTrue(result.isSuccess)
            assertEquals(UserMock.mockUser(), result.getOrNull())
        }
    }

    @Test
    fun `getUsers repository e base off deve retornar falha`() {
        runBlocking {
            //prepare
            val repository: UserRepository = mockk()
            val exception = Exception("mockError")
            val useCase = UserUseCase(repository)
            coEvery { repository.getUsers() } throws exception

            //execute

            val result = useCase.getUsers()

            //validate
            assertTrue(result.isFailure)
            assertEquals(exception, result.exceptionOrNull())
        }
    }
}