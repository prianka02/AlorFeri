package com.test.alorferi.repository

import android.util.Log
import com.test.alorferi.api.ApiService
import com.test.alorferi.api.ApiState
import com.test.alorferi.data.authentication.LoginRequest
import com.test.alorferi.data.authentication.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor (private val apiService: ApiService) {

    fun login(
        user: LoginRequest
    ): Flow<ApiState<LoginResponse>> = flow {
        try {
            emit(ApiState.Loading) // Emit loading state
            val response = apiService.login(user) // Make the network request
            Log.d("Repository", response.toString())
            emit(ApiState.Success(response))

        } catch (e: Exception) {
            emit(ApiState.Error(e.message ?: "Unknown Error")) // Emit error state
            Log.d("Error", e.toString())
        }
    }.flowOn(Dispatchers.IO)
}