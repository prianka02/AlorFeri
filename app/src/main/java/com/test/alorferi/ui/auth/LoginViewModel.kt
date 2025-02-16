package com.test.alorferi.ui.auth

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _userMobileText = MutableStateFlow(TextFieldValue(""))
    val userMobileText : StateFlow<TextFieldValue> = _userMobileText

    private val _userPasswordText = MutableStateFlow(TextFieldValue(""))
    val userPasswordText: StateFlow<TextFieldValue> = _userPasswordText

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onPhoneNoChange(newPhoneNo : TextFieldValue){
        _userMobileText.value = newPhoneNo
    }

    fun onPasswordChange(newPassword : TextFieldValue){
        _userPasswordText.value = newPassword
    }
}