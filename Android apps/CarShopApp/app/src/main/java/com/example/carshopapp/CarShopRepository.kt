package com.example.carshopapp

class CarShopRepository (private val db: CarShopDatabase) {

    suspend fun insert(items: CarShopItems) = db.getCarShopDao().insert(items)
    suspend fun delete(items: CarShopItems) = db.getCarShopDao().delete(items)

    fun getAllItems() = db.getCarShopDao().getAllCarShopItems()
}