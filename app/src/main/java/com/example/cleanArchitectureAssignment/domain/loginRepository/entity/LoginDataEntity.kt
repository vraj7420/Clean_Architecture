package com.example.cleanArchitectureAssignment.domain.loginRepository.entity

data class LoginDataEntity(
    val DeactivationRiskPercentage: Int=0,
    val Email: String="",
    val Name: String="",
    val Note: String="",
    val PhoneNumber: String="",
    val ProfileImageURL: String="",
    val UserAddress_Address: String="",
    val UserAddress_Latitude: String="",
    val UserAddress_Longitude: String="",
    val UserAddress_ZipCode: String="",
    val UserID: String="",
    val token_type: String="",
    val access_token:String=""
)