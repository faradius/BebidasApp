package com.alex.bebidasapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alex.bebidasapp.data.local.dao.DrinkDao
import com.alex.bebidasapp.data.local.entity.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun drinkDao():DrinkDao

    companion object{
        private var INSTANCE: AppDataBase? = null

        //Room.inMemoryDatabaseBuilder -> Guardado en memoria
        fun getDatabase(context: Context): AppDataBase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "drinks.db"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}