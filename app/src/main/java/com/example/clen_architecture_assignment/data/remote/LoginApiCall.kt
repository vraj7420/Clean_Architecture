package com.example.clen_architecture_assignment.data.remote

import com.example.clen_architecture_assignment.Utils
import com.example.clen_architecture_assignment.data.model.LoginData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApiCall {
    @FormUrlEncoded
    @POST("Token")
    suspend fun getProfileData(
        @Field("DeviceType") deviceType: Int = 1,
        @Field("grant_type")grantType:String=Utils.grantType,
        @Field("username") email:String,
        @Field("password") password:String,
        @Field("DeviceToken") DeviceToken:String=Utils.DeviceToken,
        @Field("isPartner")isPartner:Boolean=false,
        @Field( "LoginUserType") LoginUserType:Int=0
       ): LoginData

}