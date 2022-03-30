package com.example.cleanArchitectureAssignment.domain.loginRepository.usecase

import com.example.cleanArchitectureAssignment.Resource
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity
import com.example.cleanArchitectureAssignment.domain.loginRepository.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(private val repository: LoginRepository) {
    operator fun invoke(email: String, password: String): Flow<Resource<LoginDataEntity>> = flow {
        try {
            emit(Resource.Loading())
            val loginData = repository.getLoginUserData(email, password)
            emit(Resource.Success(loginData))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.message()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: Exception) {
        }
    }
}


