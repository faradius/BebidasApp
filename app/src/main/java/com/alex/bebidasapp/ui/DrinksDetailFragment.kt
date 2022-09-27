package com.alex.bebidasapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alex.bebidasapp.R
import com.alex.bebidasapp.data.local.AppDataBase
import com.alex.bebidasapp.data.local.entity.DrinkEntity
import com.alex.bebidasapp.data.network.api.DrinksDataSource
import com.alex.bebidasapp.utils.Constants
import com.alex.bebidasapp.data.network.model.Drink
import com.alex.bebidasapp.databinding.FragmentDrinksDetailBinding
import com.alex.bebidasapp.repository.DrinkRepositoryImpl
import com.alex.bebidasapp.ui.viewmodel.MainViewModel
import com.alex.bebidasapp.ui.viewmodel.VMFactory
import com.bumptech.glide.Glide

class DrinksDetailFragment : Fragment() {

    private lateinit var binding: FragmentDrinksDetailBinding
    private lateinit var drink: Drink
    private val viewModel by viewModels<MainViewModel> { VMFactory(
        DrinkRepositoryImpl(
            DrinksDataSource(AppDataBase.getDatabase(requireActivity().applicationContext))
        )
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable(Constants.DRINK_KEY)!!
            Log.d("Details Frag", "$drink ")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drinks_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDrinksDetailBinding.bind(view)

        with(binding){
            Glide.with(requireContext()).load(drink.image).into(ivDrink)
            tvDrinkName.text = drink.name
            tvDrinkDescription.text = drink.description

            if(drink.hasAlcohol == "Non alcoholic"){
                tvDrinkHasAlcohol.text = "Drink Non Alcoholic"
            }else{
                tvDrinkHasAlcohol.text = "Drink Alcoholic"
            }

            btnSaveDrink.setOnClickListener {
                viewModel.saveFavoriteDrink(DrinkEntity(drink.id, drink.image, drink.name, drink.description, drink.hasAlcohol))
                Toast.makeText(requireContext(), "Save drink to favorites", Toast.LENGTH_SHORT).show()
            }
        }

    }

}