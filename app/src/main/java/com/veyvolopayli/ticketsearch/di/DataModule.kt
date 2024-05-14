package com.veyvolopayli.ticketsearch.di

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.veyvolopayli.data.remote.TicketsApi
import com.veyvolopayli.data.repository.MainRepositoryImpl
import com.veyvolopayli.data.repository.StorageRepositoryImpl
import com.veyvolopayli.domain.repository.MainRepository
import com.veyvolopayli.domain.repository.StorageRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<TicketsApi> {
        val okHttpClient = OkHttpClient.Builder().build()
        val jsonConverterFactory = Json.asConverterFactory("application/json".toMediaType())
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .client(okHttpClient)
            .addConverterFactory(jsonConverterFactory)
            .build()
            .create(TicketsApi::class.java)
    }

    single<MainRepository> {
        MainRepositoryImpl(ticketsApi = get())
    }

    single<SharedPreferences> {
        androidContext().getSharedPreferences("prefs", MODE_PRIVATE)
    }

    single<StorageRepository> {
        StorageRepositoryImpl(prefs = get())
    }

}