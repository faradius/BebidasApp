package com.alex.bebidasapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.bebidasapp.R
import com.alex.bebidasapp.base.BaseViewHolder
import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.databinding.DrinksRowBinding
import com.bumptech.glide.Glide

class MainAdapter(
    private val context: Context,
    private val drinksList: List<Drink>,
    private val itemClickListener: OnDrinkClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnDrinkClickListener {
        fun onDrinkClick(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = DrinksRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(drinksList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    inner class MainViewHolder(private val binding: DrinksRowBinding) :
        BaseViewHolder<Drink>(binding.root) {

        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.image).centerCrop().into(binding.ivDrinks)
            binding.tvTitle.text = item.name
            binding.tvDescription.text = item.description

            binding.root.setOnClickListener { itemClickListener.onDrinkClick(item) }
        }

    }
}