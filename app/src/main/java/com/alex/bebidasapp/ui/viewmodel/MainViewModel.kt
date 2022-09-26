package com.alex.bebidasapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.alex.bebidasapp.domain.Repo
import com.alex.bebidasapp.base.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo):ViewModel() {

    private val drinksData = MutableLiveData<String>()

    fun setDrink(drinkName:String){
        drinksData.value = drinkName
    }

    init {
        setDrink("margarita")
    }

    val fetchDrinksList = drinksData.distinctUntilChanged().switchMap { drinkName ->
        liveData(Dispatchers.IO) {
            //Es como un postValue
            emit(Resource.Loading())
            try {
                emit(repo.getDrinksList(drinkName))
            }catch (e:Exception){
                emit(Resource.Failure(e))
                Log.d("Error", "$e")
            }
        }
    }
}

class VMFactory(private val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}