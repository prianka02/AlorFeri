package com.test.alorferi.api

import com.test.alorferi.data.authentication.LoginRequest
import com.test.alorferi.data.authentication.LoginResponse
import com.test.alorferi.data.user.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object{
        const val BASE_URL = "https://backoffice.alorferi.com/api/"
    }

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @GET("auth/me")
    suspend fun user(): UserInfo
}