package com.example.carshopapp

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CarShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert (item: CarShopItems)

    @Delete
    suspend fun delete(item: CarShopItems)

    @Query("SELECT * FROM cars_shop_items")
    fun getAllCarShopItems() : LiveData<List<CarShopItems>>
}