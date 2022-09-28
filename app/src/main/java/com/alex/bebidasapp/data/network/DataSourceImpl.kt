package com.alex.bebidasapp.data.network

import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.AppDataBase
import com.alex.bebidasapp.data.local.dao.DrinkDao
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.model.asFavoriteEntity
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val drinkDao:DrinkDao):DataSource {

    override suspend fun getDrinkByName(drinkName:String): Resource<List<Drink>> {
        return Resource.Success(RetrofitClient.webservice.getDrinksByName(drinkName).drinkList)
    }

    override suspend fun insertDrinkIntoRoom(drink:DrinkEntity){
        drinkDao.insertFavorite(drink)
    }

    override suspend fun getDrinksFavorites(): Resource<List<DrinkEntity>> {
        return Resource.Success(drinkDao.getAllFavoriteDrinks())
    }

    override suspend fun deleteDrink(drink: Drink) {
        drinkDao.deleteDrink(drink.asFavoriteEntity())
    }


    /*val generateDrinksList = Resource.Success(listOf(
        Drink("https://bakeitwithlove.com/wp-content/uploads/2021/09/Vodka-Margarita-sq.jpg","Margarita","Con azucar, vodka y nueces"),
        Drink("https://coctelia.com/wp-content/uploads/2020/06/vaso-fernet-con-coca-768x1024.jpg","Fernet","Fernet con coca y 2 hielos"),
        Drink("https://pbs.twimg.com/media/CERSHJwXIAATqjl.jpg","Toro","Toro con pritty"),
        Drink("http://2.bp.blogspot.com/_htBkEAMr654/TDpbFuSvmqI/AAAAAAAADZg/FaTWsC4nTgc/s1600/5%2BBauhaus-Buenos-Aires-Bar-Restaurant.JPG","Gancia","Gancia con sprite")

    ))*/
}