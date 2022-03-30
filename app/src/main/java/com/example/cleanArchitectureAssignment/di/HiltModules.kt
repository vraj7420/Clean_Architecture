package com.example.cleanArchitectureAssignment.di

import com.example.cleanArchitectureAssignment.data.remote.LoginDataRepository
import com.example.cleanArchitectureAssignment.domain.loginRepository.repository.LoginRepository
import com.example.cleanArchitectureAssignment.domain.loginRepository.usecase.GetLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {
    @Provides
    fun provideLoginRepository(): LoginRepository {
        return LoginDataRepository()
    }

    @Provides
    fun getLoginUse(): GetLoginUseCase {
        return GetLoginUseCase(provideLoginRepository())
    }

}