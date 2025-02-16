package com.test.alorferi.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.test.alorferi.datastore.DatastoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Or the appropriate component
object DatastoreModule {

    @Provides
    @Singleton // If you want a single instance
    fun provideDatastoreManager(
        @ApplicationContext context: Context
    ): DatastoreManager {
        return DatastoreManager(context)
    }
}