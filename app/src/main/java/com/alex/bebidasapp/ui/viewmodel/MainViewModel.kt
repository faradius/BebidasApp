package com.alex.bebidasapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.alex.bebidasapp.repository.DrinkRepository
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.model.Drink
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: DrinkRepository):ViewModel() {

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

    fun saveFavoriteDrink(drink:DrinkEntity){
        /*Con el viewModelScope va a saber cuando el fragmento del activity se destruya este viewModel va pasar por el metodo oncleared
        //limpiando toodo lo que este ha hecho por su paso por lo que va ser eliminado toodo proceso que se siga ejecutando, y el launch
        //es una corrutina de tipo context y previene hacer un scope de corrutina desde la UI*/
        viewModelScope.launch {
            repo.insertDrink(drink)
        }
    }

    fun getDrinksFavorites() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getDrinksFavorites())
        }catch (e:Exception){
            emit(Resource.Failure(e))
            Log.d("Error", "$e")
        }
    }

    fun deleteDrink(drink: Drink) {
        viewModelScope.launch {
            repo.deleteDrink(drink)
        }
    }
}

class VMFactory(private val repo: DrinkRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DrinkRepository::class.java).newInstance(repo)
    }
}