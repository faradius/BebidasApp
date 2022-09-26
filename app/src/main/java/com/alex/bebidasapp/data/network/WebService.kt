package com.alex.bebidasapp.data.network

import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.data.model.DrinkList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    //search.php?s=margarita
    @GET("search.php")
    suspend fun getDrinksByName(@Query("s") drinkName:String): DrinkList
}