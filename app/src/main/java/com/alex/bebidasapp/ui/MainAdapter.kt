package com.alex.bebidasapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.bebidasapp.core.BaseViewHolder
import com.alex.bebidasapp.data.network.model.Drink
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
            is MainViewHolder -> holder.bind(drinksList[position])
        }
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    inner class MainViewHolder(private val binding: DrinksRowBinding) :
        BaseViewHolder<Drink>(binding.root) {

        override fun bind(item: Drink) = with(binding){
            Glide.with(context).load(item.image).centerCrop().into(ivDrinks)
            tvTitle.text = item.name
            tvDescription.text = item.description

            root.setOnClickListener { itemClickListener.onDrinkClick(item) }
        }

    }
}