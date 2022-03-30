package com.example.cleanArchitectureAssignment.ui.mainscreen.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanArchitectureAssignment.R
import com.example.cleanArchitectureAssignment.Resource
import com.example.cleanArchitectureAssignment.domain.loginRepository.entity.LoginDataEntity
import com.example.cleanArchitectureAssignment.domain.loginRepository.usecase.GetLoginUseCase
import com.example.cleanArchitectureAssignment.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @ApplicationContext val ctx: Context,
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {
    var emailLiveData = MutableLiveData<String>()
    var passwordLiveData = MutableLiveData<String>()
    var btnLoginClickableLiveData = MutableLiveData(false)
    var loadingLiveData = MutableLiveData(false)
    var errorLiveData = MutableLiveData("")
    var loginDataLiveData = MutableLiveData<LoginDataEntity>()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    fun login() {
        if (Utils().checkForInternet(ctx)) {
            getLoginUseCase(
                emailLiveData.value.toString(),
                passwordLiveData.value.toString()
            ).onEach {
                when (it) {
                    is Resource.Loading -> {
                        loadingLiveData.postValue(true)
                    }
                    is Resource.Error -> {
                        errorLiveData.postValue(it.message)
                        loadingLiveData.postValue(false)
                    }
                    is Resource.Success -> {
                        loginDataLiveData.postValue(it.data as LoginDataEntity)
                        loadingLiveData.postValue(false)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            errorLiveData.postValue(ctx.getString(R.string.no_internet))
            loadingLiveData.postValue(false)
        }
    }

    fun checkValidation() {
        emailLiveData.observeForever {
            btnLoginClickableLiveData.postValue(
                emailLiveData.value?.trim()
                    ?.matches(emailPattern.toRegex()) == true && passwordLiveData.value?.length ?: 0 >= 6
            )
        }
        passwordLiveData.observeForever {
            btnLoginClickableLiveData.postValue(
                emailLiveData.value?.trim()
                    ?.matches(emailPattern.toRegex()) == true && passwordLiveData.value?.length ?: 0 >= 6
            )
        }
    }
}