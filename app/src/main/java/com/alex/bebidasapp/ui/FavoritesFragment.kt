package com.alex.bebidasapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alex.bebidasapp.R
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.AppDataBase
import com.alex.bebidasapp.data.network.api.DrinksDataSource
import com.alex.bebidasapp.repository.DrinkRepositoryImpl
import com.alex.bebidasapp.ui.viewmodel.MainViewModel
import com.alex.bebidasapp.ui.viewmodel.VMFactory


class FavoritesFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { VMFactory(
        DrinkRepositoryImpl(
            DrinksDataSource(
        AppDataBase.getDatabase(requireActivity().applicationContext))
        )
    ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDrinksFavorites().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading ->{}
                is Resource.Success ->{
                    Log.d("List_Favorites", "${result.data}")
                }
                is Resource.Failure ->{}
            }
        })
    }
}