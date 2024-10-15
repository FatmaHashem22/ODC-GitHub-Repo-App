package com.example.odcgithubrepoapp.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import com.example.odcgithubrepoapp.data.data_sources.local.data_store.DataStorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context : Context) : DataStorePreference {
        return DataStorePreference(context)
    }
}