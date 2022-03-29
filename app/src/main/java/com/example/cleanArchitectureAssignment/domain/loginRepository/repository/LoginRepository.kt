package com.example.cleanArchitectureAssignment.domain.loginRepository.repository

import com.example.cleanArchitectureAssignment.data.model.LoginData


interface LoginRepository {
    suspend fun getLoginUserData(email:String,password:String): LoginData
}