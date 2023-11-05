package com.example.todoer

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [Card::class],version=1)
abstract class Database : RoomDatabase() {
    abstract fun dao():DAO
}