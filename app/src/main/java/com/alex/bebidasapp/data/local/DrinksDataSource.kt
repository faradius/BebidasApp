package com.alex.bebidasapp.data.local

import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.vo.Resource

class DrinksDataSource {

    val generateDrinksList = Resource.Success(listOf(
        Drink("https://bakeitwithlove.com/wp-content/uploads/2021/09/Vodka-Margarita-sq.jpg","Margarita","Con azucar, vodka y nueces"),
        Drink("https://coctelia.com/wp-content/uploads/2020/06/vaso-fernet-con-coca-768x1024.jpg","Fernet","Fernet con coca y 2 hielos"),
        Drink("https://pbs.twimg.com/media/CERSHJwXIAATqjl.jpg","Toro","Toro con pritty"),
        Drink("http://2.bp.blogspot.com/_htBkEAMr654/TDpbFuSvmqI/AAAAAAAADZg/FaTWsC4nTgc/s1600/5%2BBauhaus-Buenos-Aires-Bar-Restaurant.JPG","Gancia","Gancia con sprite")

    ))
}