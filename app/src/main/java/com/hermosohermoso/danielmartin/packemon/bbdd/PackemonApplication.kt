package com.hermosohermoso.danielmartin.packemon.bbdd

import android.app.Application

class PackemonApplication: Application(){
    val database: PackemonDataBase by lazy {
        PackemonDataBase.getDatabase(this)
    }

    override fun onCreate(){
        super.onCreate()
    }
}