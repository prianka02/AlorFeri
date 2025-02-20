package com.test.alorferi.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.alorferi.api.ApiState
import com.test.alorferi.data.user.UserInfo
import com.test.alorferi.repository.AuthRepository
import com.test.alorferi.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    ) : ViewModel(){

    val userResponseFlow = MutableStateFlow<UserInfo?>(null)
    val isLoading = MutableStateFlow(true)
    val errorMessage = MutableStateFlow<String?>(null)

    private val _nameText = MutableStateFlow("")
    val name = _nameText.asStateFlow()

    private val _emailText = MutableStateFlow("")
    val email = _emailText.asStateFlow()

    private val _dobText = MutableStateFlow("")
    val dob = _dobText.asStateFlow()

    fun onNameChange(newName: String){
        _nameText.value = newName.toString()
    }

    fun onEmailChange(newEmail: String){
        _emailText.value = newEmail.toString()
    }

    fun onDOBChange(newDOB : String){
        _dobText.value = newDOB.toString()
    }

    init {
        getUserInfo()
    }


    fun getUserInfo() {
        viewModelScope.launch {
            userRepository.getUserInfo().collect { resource ->
                when (resource) {
                    is ApiState.Loading -> {
                        isLoading.value = true // Set loading state
                    }

                    is ApiState.Success -> {
                        isLoading.value = false // Stop loading
                        userResponseFlow.value = resource.data // Set news data
                        Log.d("Viewmodel", resource.data.toString())
                    }

                    is ApiState.Error -> {
                        isLoading.value = false // Stop loading
                        errorMessage.value = resource.message // Set error message
                    }
                }
            }
        }

    }

    fun updateUserInfo(){
        viewModelScope.launch {

        }
    }
}