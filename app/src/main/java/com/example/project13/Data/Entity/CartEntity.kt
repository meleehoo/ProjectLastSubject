package com.example.project13.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartEntity(
    @PrimaryKey
    val title: String,
    val price: Double,
    val numberInCart: Int,
    val picUrl: String
)
