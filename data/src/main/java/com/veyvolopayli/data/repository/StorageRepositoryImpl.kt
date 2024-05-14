package com.veyvolopayli.data.repository

import android.content.SharedPreferences
import com.veyvolopayli.domain.repository.StorageRepository

class StorageRepositoryImpl(private val prefs: SharedPreferences) : StorageRepository {
    override suspend fun saveValue(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override suspend fun getValue(key: String): String? {
        return prefs.getString(key, null)
    }
}