package com.alex.bebidasapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.bebidasapp.R
import com.alex.bebidasapp.utils.Constants
import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.databinding.FragmentDrinksDetailBinding
import com.bumptech.glide.Glide

class DrinksDetailFragment : Fragment() {

    private lateinit var binding: FragmentDrinksDetailBinding
    private lateinit var drink: Drink

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
        }



    }

}