package com.example.cleanArchitectureAssignment.domain.loginRepository.repository

import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity

interface LoginRepository {
    suspend fun getLoginUserData(email: String, password: String): LoginDataEntity
}