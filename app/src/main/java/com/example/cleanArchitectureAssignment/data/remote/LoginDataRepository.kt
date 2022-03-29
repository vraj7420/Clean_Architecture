package com.example.cleanArchitectureAssignment.data.remote

import com.example.cleanArchitectureAssignment.data.model.LoginData
import com.example.cleanArchitectureAssignment.data.baseService.BaseService
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity
import com.example.cleanArchitectureAssignment.domain.loginRepository.repository.LoginRepository

class LoginDataRepository: LoginRepository{
    override suspend fun getLoginUserData(email: String, password: String): LoginData {
        return  BaseService().getBaseApi().getProfileData(email = email, password = password)

    }
}