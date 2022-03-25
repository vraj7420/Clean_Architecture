package com.example.clen_architecture_assignment.domian.mainactivity.usecase

import com.example.clen_architecture_assignment.Resource
import com.example.clen_architecture_assignment.data.model.LoginData
import com.example.clen_architecture_assignment.domian.mainactivity.repository.LoginRepository
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
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.response()?.code().toString()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage?:"" ))
        } catch (e: Exception) {

        }
    }
}