package com.example.clen_architecture_assignment.domian.loginRepository.model

data class LoginDataEntity(
    val DeactivationRiskPercentage: Int=0,
    val Email: String="",
    val Name: String="",
    val Note: String="",
    val NumberOfDaysToReactivate: String="",
    val NumberOfSuspendedDays: String="",
    val PhoneNumber: String="",
    val ProfileImageURL: String="",
    val UserAddress_Address: String="",
    val UserAddress_City: String="",
    val UserAddress_Country: String="",
    val UserAddress_ID: String="",
    val UserAddress_Latitude: String="",
    val UserAddress_Longitude: String="",
    val UserAddress_State: String="",
    val UserAddress_ZipCode: String="",
    val UserID: String="",
    val VehicleStandardStatus: String="",
    val token_type: String="",
    val access_token:String=""
)