package com.alex.bebidasapp.repository

import com.alex.bebidasapp.data.network.api.DrinksDataSource
import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.entity.DrinkEntity

class DrinkRepositoryImpl(private val dataSource: DrinksDataSource):DrinkRepository {

    override suspend fun getDrinksList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getDrinksFavorites(): Resource<List<DrinkEntity>> {
        return dataSource.getDrinksFavorites()
    }

    override suspend fun insertDrink(drink: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drink)
    }
}