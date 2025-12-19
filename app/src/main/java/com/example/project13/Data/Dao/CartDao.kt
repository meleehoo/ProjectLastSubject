package com.example.project13.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.project13.Data.Entity.CartEntity

@Dao
interface CartDao {

    @Query("SELECT * FROM cart")
    fun getAll(): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: CartEntity)

    @Update
    fun update(item: CartEntity)

    @Query("DELETE FROM cart")
    fun clear()
}
