package com.example.clen_architecture_assignment.di

import com.example.clen_architecture_assignment.data.repository.LoginDataRepository
import com.example.clen_architecture_assignment.domian.mainactivity.repository.LoginRepository
import com.example.clen_architecture_assignment.domian.mainactivity.usecase.GetLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {
    @Provides
    fun  provideLoginRepository():LoginRepository{
        return LoginDataRepository()
    }
    @Provides
    fun getLoginUseCase():GetLoginUseCase{
        return GetLoginUseCase(provideLoginRepository())
    }
}