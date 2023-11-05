package com.example.carshopapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CarShopItems::class], version = 1)
abstract class CarShopDatabase : RoomDatabase() {

    abstract fun getCarShopDao(): CarShopDao

    companion object{
        @Volatile
        private var instance: CarShopDatabase? = null
        private val Lock = Any()

        operator  fun invoke(context: Context) = instance ?: synchronized(Lock){
            instance ?: createDatabase(context).also{
                instance= it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CarShopDatabase::class.java,
                "CarShop.db"
            ).build()
    }
}