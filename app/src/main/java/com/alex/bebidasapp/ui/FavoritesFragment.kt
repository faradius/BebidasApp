package com.alex.bebidasapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.bebidasapp.R
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.databinding.FragmentFavoritesBinding
import com.alex.bebidasapp.ui.viewmodel.MainViewModel
//import com.alex.bebidasapp.ui.viewmodel.VMFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), MainAdapter.OnDrinkClickListener {

    private lateinit var binding:FragmentFavoritesBinding
    private lateinit var favoritesAdapter: MainAdapter

    private val viewModel by viewModels<MainViewModel>()/*{ VMFactory(
        DrinkRepositoryImpl(
            DataSourceImpl(
        AppDataBase.getDatabase(requireActivity().applicationContext))
        )
    ) }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoritesBinding.bind(view)

        setupRecyclerView()
        setupObservers()

    }

    private fun setupObservers(){
        viewModel.getDrinksFavorites().observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.Loading ->{}
                is Resource.Success ->{
                    //Aqui estoy conviertiendo una lista de bebidas de tipo drinkEntity una lista de tipo drink con el map
                    val listDrinkFavorites = result.data.map {
                        Drink(it.id,it.image,it.name,it.description,it.hasAlcohol)
                    }

                    favoritesAdapter = MainAdapter(requireContext(),listDrinkFavorites, this)
                    binding.rvDrinksFavorites.adapter = favoritesAdapter


                    Log.d("List_Favorites", "${result.data}")
                }
                is Resource.Failure ->{}
            }
        })
    }

    private fun setupRecyclerView(){
        binding.rvDrinksFavorites.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrinksFavorites.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onDrinkClick(drink: Drink, position:Int) {

        //Elimina pero no se refleja el cambio en el adaptador
        /*viewModel.deleteDrink(drink)
        favoritesAdapter.notifyItemRangeRemoved(position,binding.rvDrinksFavorites.adapter?.itemCount!!)
        favoritesAdapter.notifyItemRemoved(position)
        Toast.makeText(requireContext(), "Drink eliminated", Toast.LENGTH_SHORT).show()
        */
    }
}