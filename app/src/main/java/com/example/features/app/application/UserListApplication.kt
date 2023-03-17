package com.example.features.app.application

import android.app.Application
import com.example.features.users.di.UserDI.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class UserListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UserListApplication)
            modules(
                listOf(
                    userModule
                )
            )
        }
    }
}