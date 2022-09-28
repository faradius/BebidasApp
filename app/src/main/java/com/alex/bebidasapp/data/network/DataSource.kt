package com.alex.bebidasapp.data.network

import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.model.Drink

interface DataSource {
    suspend fun getDrinkByName(drinkName:String): Resource<List<Drink>>
    suspend fun insertDrinkIntoRoom(drink: DrinkEntity)
    suspend fun getDrinksFavorites(): Resource<List<DrinkEntity>>
    suspend fun deleteDrink(drink: Drink)
}