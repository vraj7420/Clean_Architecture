package com.example.cleanArchitectureAssignment.domain.loginRepository.usecase

import com.example.cleanArchitectureAssignment.Resource
import com.example.cleanArchitectureAssignment.data.model.LoginData
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
            val data = repository.getLoginUserData(email,password)
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?:""))
        } catch (e: IOException) {
            emit(Resource.Error(message ="Check your internet"))
        } catch (e: Exception) {
        }
    }
}


