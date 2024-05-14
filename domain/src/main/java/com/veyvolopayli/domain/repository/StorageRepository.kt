package com.veyvolopayli.domain.repository

interface StorageRepository {

    suspend fun saveValue(key: String, value: String)

    suspend fun getValue(key: String): String?

}