package com.example.cleanArchitectureAssignment.data

import com.example.cleanArchitectureAssignment.data.model.LoginData
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity

    fun mapLoginDataResponse(loginData:LoginData):LoginDataEntity{
        return LoginDataEntity(
            loginData.DeactivationRiskPercentage,
            loginData.Email,
            loginData.Name,
            loginData.Note,
            loginData.PhoneNumber,
            loginData.ProfileImageURL,
            loginData.UserAddress_Address,
            loginData.UserAddress_Latitude,
            loginData.UserAddress_Longitude,
            loginData.UserAddress_ZipCode,
            loginData.UserID,
            loginData.token_type,
            loginData.access_token
        )
    }

