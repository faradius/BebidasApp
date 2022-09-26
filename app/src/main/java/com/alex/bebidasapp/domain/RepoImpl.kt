package com.alex.bebidasapp.domain

import com.alex.bebidasapp.data.network.DrinksDataSource
import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.base.Resource

class RepoImpl(private val dataSource: DrinksDataSource):Repo {

    override suspend fun getDrinksList(drinkName:String): Resource<List<Drink>> {
        return dataSource.getDrinkByName(drinkName)
    }
}