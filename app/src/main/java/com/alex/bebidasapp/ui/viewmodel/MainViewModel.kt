package com.alex.bebidasapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.alex.bebidasapp.domain.Repo
import com.alex.bebidasapp.vo.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo):ViewModel() {

    val fetchDrinksList = liveData(Dispatchers.IO) {
        //Es como un postValue
        emit(Resource.Loading())
        try {
            emit(repo.getDrinksList())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class VMFactory(private val repo: Repo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repo::class.java).newInstance(repo)
    }
}