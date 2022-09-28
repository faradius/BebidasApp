package com.alex.bebidasapp.repository

import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.entity.DrinkEntity

interface DrinkRepository {
    suspend fun getDrinksList(drinkName:String): Resource<List<Drink>>
    suspend fun getDrinksFavorites():Resource<List<DrinkEntity>>
    suspend fun insertDrink(drink:DrinkEntity)
    suspend fun deleteDrink(drink: Drink)
}