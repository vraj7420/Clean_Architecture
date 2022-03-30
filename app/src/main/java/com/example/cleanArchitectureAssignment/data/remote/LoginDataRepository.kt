package com.example.cleanArchitectureAssignment.data.remote

import com.example.cleanArchitectureAssignment.data.base.BaseService
import com.example.cleanArchitectureAssignment.data.mapLoginDataResponse
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity
import com.example.cleanArchitectureAssignment.domain.loginRepository.repository.LoginRepository

class LoginDataRepository : LoginRepository {

    override suspend fun getLoginUserData(
        email: String,
        password: String
    ): LoginDataEntity {
        return BaseService().getBaseApi().getProfileData(email = email, password = password).let {
            mapLoginDataResponse(it)
        }
    }
}