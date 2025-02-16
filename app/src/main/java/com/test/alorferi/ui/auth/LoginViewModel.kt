package com.test.alorferi.ui.auth

import android.content.Context
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.alorferi.datastore.DatastoreManager
import com.test.alorferi.api.ApiState
import com.test.alorferi.data.authentication.LoginRequest
import com.test.alorferi.data.authentication.LoginResponse
import com.test.alorferi.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
   private val authRepository: AuthRepository,
   private val datastoreManager: DatastoreManager
): ViewModel() {

    private val _userMobileText = MutableStateFlow(TextFieldValue(""))
    val userMobileText : StateFlow<TextFieldValue> = _userMobileText

    private val _userPasswordText = MutableStateFlow(TextFieldValue(""))
    val userPasswordText: StateFlow<TextFieldValue> = _userPasswordText

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val errorMessage = MutableStateFlow("")
    val loginResponse = MutableStateFlow<LoginResponse?>(null)

    fun onPhoneNoChange(newPhoneNo : TextFieldValue){
        _userMobileText.value = newPhoneNo
    }

    fun onPasswordChange(newPassword : TextFieldValue){
        _userPasswordText.value = newPassword
    }

    private fun validateForm(): Boolean {
        val mobileNo = _userMobileText.value.text.trim()
        val password = _userPasswordText.value.text.trim()

        return when {
            mobileNo.isEmpty() -> {
                errorMessage.value = "Mobile number cannot be empty"
                false
            }
            mobileNo.length < 12 -> {
                errorMessage.value = "Mobile number must be at least 11 digits"
                false
            }
            password.isEmpty() -> {
                errorMessage.value = "Password cannot be empty"
                false
            }
            password.length < 6 -> { // Example minimum password length validation
                errorMessage.value = "Password must be at least 6 characters"
                false
            }
            else -> {
                errorMessage.value = "" // No error
                true
            }
        }

    }

    fun onCLickLogin(context: Context) {
//        if (validateForm()) {

//            Log.d("Viewmodel", " IF")
            val loginInstance = LoginRequest(
                username = _userMobileText.value.text,
                password = _userPasswordText.value.text
            )
            Log.d("Viewmodel", loginInstance.toString())
            viewModelScope.launch {
                authRepository.login(
                    loginInstance
                ).collect { resource ->
                    when (resource) {
                        is ApiState.Loading -> {
                            _isLoading.value = true // Set loading state
                        }

                        is ApiState.Success -> {
                            _isLoading.value = false // Stop loading
                            loginResponse.value = resource.data // Set news data
                            Log.d("Viewmodel", resource.data.toString())


                            resource.data.data.token.let {
                                datastoreManager.saveToken(it)
                                // Inject token into the API client
                                Log.d("Login Response", resource.data.data.token)
                            }
                        }

                        is ApiState.Error -> {
                            _isLoading.value = false // Stop loading
                            errorMessage.value = resource.message
                            Log.d("Login Response", resource.message)
                        }
                    }
                }
            }
//        } else {
//
//            Log.d("Viewmodel", " else")
//            Toast.makeText(context, "Enter Valid Data", Toast.LENGTH_SHORT).show()
//        }
    }
}