package com.alex.bebidasapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alex.bebidasapp.R
import com.alex.bebidasapp.utils.Constants
import com.alex.bebidasapp.data.network.api.DrinksDataSource
import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.databinding.FragmentMainBinding
import com.alex.bebidasapp.repository.DrinkRepositoryImpl
import com.alex.bebidasapp.ui.viewmodel.MainViewModel
import com.alex.bebidasapp.ui.viewmodel.VMFactory
import com.alex.bebidasapp.core.Resource
import com.alex.bebidasapp.data.local.AppDataBase


class MainFragment : Fragment(),MainAdapter.OnDrinkClickListener {

    private lateinit var binding: FragmentMainBinding

    private val viewModel by viewModels<MainViewModel> { VMFactory(DrinkRepositoryImpl(DrinksDataSource(
        AppDataBase.getDatabase(requireActivity().applicationContext)))) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setupRecyclerView()
        setupSearchView()
        setupObservers()

        binding.btnGoToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_favoritesFragment)
        }

    }


    private fun setupObservers(){
        viewModel.fetchDrinksList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvDrinks.adapter = MainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure ->{
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Ocurrio un error al traer los datos ${result.exception}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setDrink(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun setupRecyclerView(){
        binding.rvDrinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDrinks.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    //No necesita el parametro position
    override fun onDrinkClick(drink: Drink, position:Int) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.DRINK_KEY,drink)
        findNavController().navigate(R.id.action_mainFragment_to_drinksDetailFragment,bundle)
    }

}