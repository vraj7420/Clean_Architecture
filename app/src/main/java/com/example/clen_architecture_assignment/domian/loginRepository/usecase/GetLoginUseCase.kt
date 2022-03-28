package com.example.clen_architecture_assignment.domian.loginRepository.usecase

import com.example.clen_architecture_assignment.Resource
import com.example.clen_architecture_assignment.data.model.LoginData
import com.example.clen_architecture_assignment.domian.loginRepository.model.LoginDataEntity
import com.example.clen_architecture_assignment.domian.loginRepository.repository.LoginRepository
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
            val loginEntity=LoginDataEntity(data.DeactivationRiskPercentage,data.Email,data.Name,
                data.Note,data.NumberOfDaysToReactivate,data.NumberOfSuspendedDays,data.PhoneNumber,data.ProfileImageURL
            ,data.UserAddress_Address,data.UserAddress_City,data.UserAddress_Country,data.UserAddress_ID,data.UserAddress_Latitude,data.UserAddress_Longitude,data.UserAddress_State,data.UserAddress_ZipCode
            ,data.UserID,data.VehicleStandardStatus,data.token_type,data.access_token)
            emit(Resource.Success(loginEntity))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.response()?.code().toString()))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage?:"" ))
        } catch (e: Exception) {

        }
    }
}


