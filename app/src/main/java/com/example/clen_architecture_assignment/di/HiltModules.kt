package com.example.clen_architecture_assignment.di

import com.example.clen_architecture_assignment.data.datarepository.LoginDataRepository
import com.example.clen_architecture_assignment.domian.loginRepository.repository.LoginRepository
import com.example.clen_architecture_assignment.domian.loginRepository.usecase.GetLoginUseCase
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