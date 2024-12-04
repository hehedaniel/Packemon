package com.hermosohermoso.danielmartin.packemon.bbdd

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.hermosohermoso.danielmartin.packemon.preferences.PackemonPreferences

val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCES")

class PackemonApplication: Application(){
    val database: PackemonDataBase by lazy {
        PackemonDataBase.getDatabase(this)
    }

    lateinit var gridNumber: PackemonPreferences

    override fun onCreate(){
        super.onCreate()
        gridNumber = PackemonPreferences(dataStore)
    }
}