package com.hermosohermoso.danielmartin.packemon.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(PokemonDDBB::class), version = 1)
abstract class PackemonDataBase: RoomDatabase(){
    abstract fun packemonDao(): PackemonDAO

    companion object{
        @Volatile
        private var INSTANCE: PackemonDataBase? = null

        fun getDatabase(context: Context): PackemonDataBase{
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    PackemonDataBase::class.java,
                    "packemon_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{
                        INSTANCE = it
                    }
            }
        }
    }
}