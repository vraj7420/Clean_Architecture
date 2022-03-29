package com.example.cleanArchitectureAssignment.data.remote

import com.example.cleanArchitectureAssignment.utils.Utils
import com.example.cleanArchitectureAssignment.data.model.LoginData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Single

interface LoginApiCall {
    @FormUrlEncoded
    @POST("Token")
    suspend fun getProfileData(
        @Field("DeviceType") deviceType: Int = 1,
        @Field("grant_type")grantType:String= Utils.grantType,
        @Field("username") email:String,
        @Field("password") password:String,
        @Field("DeviceToken") deviceToken:String= Utils.DeviceToken,
        @Field("isPartner")isPartner:Boolean=false,
        @Field( "LoginUserType") loginUserType:Int=0
       ): LoginData

}