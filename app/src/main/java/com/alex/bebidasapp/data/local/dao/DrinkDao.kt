package com.alex.bebidasapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alex.bebidasapp.data.local.entity.DrinkEntity

@Dao
interface DrinkDao {

    @Query("SELECT * FROM Drink_Entity")
    suspend fun getAllFavoriteDrinks():List<DrinkEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(drink: DrinkEntity)

}