package com.example.features.users.di

import com.example.features.app.service.WebService.URL_GITHUB
import com.example.features.app.service.WebService.service
import com.example.features.users.data.mapper.UserMapper
import com.example.features.users.data.repository.UserRepositoryImpl
import com.example.features.users.data.source.remote.data.UserRemoteData
import com.example.features.users.data.source.remote.service.UserRemoteService
import com.example.features.users.domain.repository.UserRepository
import com.example.features.users.domain.usecase.UserUseCase
import com.example.features.users.presentation.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val userModule = module {
    viewModel { UserViewModel(useCase = get()) }
    factory { UserUseCase(userRepository = get()) }
    factory { UserMapper() }
    factory<UserRepository> {
        UserRepositoryImpl(
            mapper = get(),
            remoteData = get()
        )
    }
    single<UserRemoteData> { service<UserRemoteService>(URL_GITHUB) }
}
