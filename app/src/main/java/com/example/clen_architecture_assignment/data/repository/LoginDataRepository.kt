package com.example.clen_architecture_assignment.data.repository

import com.example.clen_architecture_assignment.data.model.LoginData
import com.example.clen_architecture_assignment.di.BaseService
import com.example.clen_architecture_assignment.domian.mainactivity.repository.LoginRepository

class LoginDataRepository: LoginRepository{
    override suspend fun getLoginUserData(email: String, password: String): LoginData {
        return  BaseService().getBaseApi().getProfileData(email = email, password = password)

    }
}