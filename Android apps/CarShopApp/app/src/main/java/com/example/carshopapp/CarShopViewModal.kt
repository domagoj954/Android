package com.example.carshopapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CarShopViewModal(private val repository: CarShopRepository) : ViewModel() {

    fun insert(items: CarShopItems) = GlobalScope.launch {
        repository.insert(items)
    }


    fun delete(items: CarShopItems) = GlobalScope.launch {
        repository.delete(items)
    }

    fun getAllCarShopItems()= repository.getAllItems()
}