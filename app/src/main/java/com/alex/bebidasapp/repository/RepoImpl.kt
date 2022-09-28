package com.alex.bebidasapp.repository

import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.DataSource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSource: DataSource):Repo {

    override suspend fun getDrinksList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }

    override suspend fun getDrinksFavorites(): Resource<List<DrinkEntity>> {
        return dataSource.getDrinksFavorites()
    }

    override suspend fun insertDrink(drink: DrinkEntity) {
        dataSource.insertDrinkIntoRoom(drink)
    }

    override suspend fun deleteDrink(drink: Drink) {
        dataSource.deleteDrink(drink)
    }
}