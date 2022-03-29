package com.example.cleanArchitectureAssignment

sealed class Resource<T>(val data: Any? = null, val message: String? = null) {
    class Success<T>(data:Any) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}