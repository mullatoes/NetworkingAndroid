package com.kyeiiih.networking.di

import android.app.Application
import com.kyeiiih.networking.network.UserService
import com.kyeiiih.networking.viewmodel.UserViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
/*

@HiltAndroidApp
class MyApplication : Application() {
}

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    fun provideUserViewModel(
        userService: UserService
    ): UserViewModel {
        return UserViewModel(userService)
    }
}*/
