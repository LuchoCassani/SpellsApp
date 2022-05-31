package com.cassani.phrasesapp.di

import com.cassani.phrasesapp.data.network.SpellApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://harry-potter-api-production.up.railway.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSpellApiClient(retrofit: Retrofit):SpellApiClient{
        return retrofit.create(SpellApiClient::class.java)
    }
}