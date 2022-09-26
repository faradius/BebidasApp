package com.alex.bebidasapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alex.bebidasapp.R
import com.alex.bebidasapp.data.model.Drink

class DrinksDetailFragment : Fragment() {
    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            drink = it.getParcelable("drink")!!
            Log.d("Details Frag", "$drink ")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drinks_detail, container, false)
    }

}