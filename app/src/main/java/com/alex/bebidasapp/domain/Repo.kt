package com.alex.bebidasapp.domain

import com.alex.bebidasapp.data.model.Drink
import com.alex.bebidasapp.vo.Resource

interface Repo {
    fun getDrinksList(): Resource<List<Drink>>
}