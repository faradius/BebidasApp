package com.alex.bebidasapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.model.Drink

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drink_Entity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drink: DrinkEntity)
}