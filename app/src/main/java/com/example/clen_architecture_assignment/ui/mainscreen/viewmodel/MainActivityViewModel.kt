package com.example.clen_architecture_assignment.ui.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clen_architecture_assignment.Resource
import com.example.clen_architecture_assignment.data.model.LoginData
import com.example.clen_architecture_assignment.domian.mainactivity.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(private val getLoginUseCase: GetLoginUseCase)  :ViewModel(){
    var email=MutableLiveData<String>()
    var password=MutableLiveData<String>()
    var btnLoginClickable=MutableLiveData(false)
    var loading=MutableLiveData(false)
    var error=MutableLiveData("")
    var loginData=MutableLiveData<LoginData?>()



    fun login(){
        getLoginUseCase(email.value.toString(), password.value.toString()).onEach {
            when(it){
                is Resource.Loading->{
                        loading.postValue(true)
                }
                is Resource.Error->{
                    error.postValue(it.message)
                    loading.postValue(false)

                }
                is Resource.Success->{
                    loginData.postValue(it.data)
                    loading.postValue(false)
                }
            }
        }.launchIn(viewModelScope)
    }

}