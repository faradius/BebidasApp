package com.alex.bebidasapp.domain

import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.base.Resource

interface Repo {
    suspend fun getDrinksList(drinkName:String): Resource<List<Drink>>
}