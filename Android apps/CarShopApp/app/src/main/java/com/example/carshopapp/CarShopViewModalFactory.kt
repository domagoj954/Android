package com.example.carshopapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CarShopViewModalFactory(private val repository: CarShopRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CarShopViewModal(repository) as T
    }
}