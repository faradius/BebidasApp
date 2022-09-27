package com.alex.bebidasapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Drink_Entity")
data class DrinkEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:String,
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "hasAlcohol")
    val hasAlcohol: String = "Non alcoholic"

)