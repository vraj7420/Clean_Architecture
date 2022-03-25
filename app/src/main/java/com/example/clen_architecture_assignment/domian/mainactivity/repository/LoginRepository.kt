package com.example.clen_architecture_assignment.domian.mainactivity.repository

import android.provider.ContactsContract
import com.example.clen_architecture_assignment.data.model.LoginData

interface LoginRepository {
    suspend fun getLoginUserData(email:String,password:String):LoginData
}