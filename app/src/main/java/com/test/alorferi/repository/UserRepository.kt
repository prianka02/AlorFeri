package com.test.alorferi.repository

import android.util.Log
import com.test.alorferi.api.ApiService
import com.test.alorferi.api.ApiState
import com.test.alorferi.data.authentication.LoginRequest
import com.test.alorferi.data.authentication.LoginResponse
import com.test.alorferi.data.user.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor (private val apiService: ApiService) {

    fun getUserInfo(): Flow<ApiState<UserInfo>> = flow {
        try {
            emit(ApiState.Loading) // Emit loading state
            val response = apiService.user() // Make the network request
            emit(ApiState.Success(response))
            Log.d("Repository getUser ", response.toString())

        } catch (e: Exception) {
            Log.d("Repository getUser error ", e.toString())

            emit(ApiState.Error(e.message ?: "Unknown Error")) // Emit error state
        }
    }.flowOn(Dispatchers.IO)

}