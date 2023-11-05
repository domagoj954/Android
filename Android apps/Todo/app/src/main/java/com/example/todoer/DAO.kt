package com.example.todoer

import androidx.room.*

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Card)

    @Update
    suspend fun updateTask(entity: Card)

    @Delete
    suspend fun deleteTask(entity: Card)

    @Query("Delete from to_do")
    suspend fun deleteAll()

    @Query("Select * from to_do")
    suspend fun getTasks():List<CardInfo>

}