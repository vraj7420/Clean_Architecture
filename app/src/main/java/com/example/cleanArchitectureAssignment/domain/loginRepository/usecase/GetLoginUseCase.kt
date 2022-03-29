package com.example.cleanArchitectureAssignment.domain.loginRepository.usecase

import com.example.cleanArchitectureAssignment.Resource
import com.example.cleanArchitectureAssignment.data.model.LoginData
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity
import com.example.cleanArchitectureAssignment.domain.loginRepository.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase@Inject constructor(private val repository:LoginRepository){
    operator fun invoke(email: String,password:String): Flow<Resource<LoginData>> = flow {
        try {
            emit(Resource.Loading())
            val loginData = repository.getLoginUserData(email,password)
            val loginDataEntity= LoginDataEntity(
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
                loginData.access_token)
            emit(Resource.Success(loginDataEntity))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?:""))
        } catch (e: IOException) {
            emit(Resource.Error(message ="Check your internet"))
        } catch (e: Exception) {
        }
    }
}


