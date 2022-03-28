package com.example.clen_architecture_assignment.ui.mainscreen.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clen_architecture_assignment.Resource
import com.example.clen_architecture_assignment.data.model.LoginData
import com.example.clen_architecture_assignment.domian.loginRepository.usecase.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor(private val getLoginUseCase: GetLoginUseCase)  :ViewModel(){
    var emailLiveData=MutableLiveData<String>()
    var passwordLiveData=MutableLiveData<String>()
    var btnLoginClickableLiveData=MutableLiveData(false)
    var loadingLiveData=MutableLiveData(false)
    var errorLiveData=MutableLiveData("")
    var loginDataLiveData=MutableLiveData<LoginData?>()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"


    init {
        emailLiveData.observeForever{
            btnLoginClickableLiveData.postValue(
                emailLiveData.value?.trim()
                    ?.matches(emailPattern.toRegex()) == true && passwordLiveData.value?.length ?:0 >= 6
            )
        }
        passwordLiveData.observeForever {
            btnLoginClickableLiveData.postValue(
                emailLiveData.value?.trim()
                    ?.matches(emailPattern.toRegex()) == true && passwordLiveData.value?.length ?:0 >= 6
            )
        }
    }

    fun login(){
        getLoginUseCase(emailLiveData.value.toString(), passwordLiveData.value.toString()).onEach {
            when(it){
                is Resource.Loading->{
                        loadingLiveData.postValue(true)
                }
                is Resource.Error->{
                    errorLiveData.postValue(it.message)
                    loadingLiveData.postValue(false)

                }
                is Resource.Success->{
                    loginDataLiveData.postValue(it.data)
                    loadingLiveData.postValue(false)
                }
            }
        }.launchIn(viewModelScope)
    }

}