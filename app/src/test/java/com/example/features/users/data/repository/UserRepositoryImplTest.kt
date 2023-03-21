package com.example.features.users.data.repository

import com.example.features.users.data.mapper.UserMapper
import com.example.features.users.data.source.remote.service.UserRemoteService
import com.example.features.users.domain.repository.UserRepository
import com.example.features.users.utils.UserMock.mockUser
import com.example.features.users.utils.UserMock.mockUserResponse
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class UserRepositoryImplementTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var mapper: UserMapper

    @MockK
    private lateinit var exception: Exception

    @MockK
    private lateinit var service: UserRemoteService

    @Test
    fun `getUsers servico com erro deve jogar uma UserServiceException`() {
        coEvery { service.getUsers() } throws exception
        val repository: UserRepository = UserRepositoryImpl(mapper, service)
        runBlocking {
            val result = repository.getUsers()
            Assert.assertTrue(result.isFailure)
        }
    }

    @Test
    fun `getUsers servico ok deve retornar user list`() {
        coEvery { service.getUsers() } returns mockUserResponse()
        coEvery { mapper.map(mockUserResponse()) } returns mockUser()

        val repository: UserRepository = UserRepositoryImpl(mapper, service)
        runBlocking {
            val result = repository.getUsers()
            Assert.assertTrue(result.isSuccess)
            assertEquals(mockUser(), result.getOrNull())
        }
    }
}
