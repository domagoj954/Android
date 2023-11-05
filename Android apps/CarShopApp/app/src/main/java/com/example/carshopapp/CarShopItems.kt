
package com.example.carshopapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// This is a data class which store data.
// Entities class create a table in database

@Entity(tableName = "cars_shop_items")

data class CarShopItems(


    @ColumnInfo(name = "itemName")
    var itemName: String,


    @ColumnInfo(name = "itemQuantity")
    var itemQuantity: Int,

    @ColumnInfo(name = "itemPrice")
    var itemPrice: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}