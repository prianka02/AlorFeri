package com.test.alorferi.api

import android.util.Log
import com.test.alorferi.datastore.DatastoreManager
import com.test.alorferi.api.ApiService.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    // Provides OkHttpClient with an interceptor for Authorization header
    @Provides
    @Singleton
    fun provideOkHttpClient(datastoreManager: DatastoreManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                // Lazily retrieve the token
                val token = runBlocking {
                    datastoreManager.getToken().firstOrNull() // Use firstOrNull to avoid blocking endlessly
                }

                Log.d("TOKEN Intercept", "Intercepted Token: $token")

                val newRequest = chain.request().newBuilder()
                    .apply {
                        if (!token.isNullOrEmpty()) {
                            addHeader("Authorization", "Bearer $token")
                        }
                    }
                    .build()

                chain.proceed(newRequest)
            }
            .build()
    }

    // Provides Retrofit instance
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Provides ApiService instance
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
