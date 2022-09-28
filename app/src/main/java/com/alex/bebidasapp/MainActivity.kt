package com.alex.bebidasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.alex.bebidasapp.data.local.dao.DrinkDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Con esta anotación hicimos que esta clase este preparada para ser inyectada, pero ojo, la clase que vallemos a inyectar aqui
//tambien debe de estar preparada, es decir, se debe cumplir dos condiciones, la primera la clase que queremos inyectar debe de estar preparada
//para realizarlo y la clase que recibirá esa inyeccion tambien debe esta preparada
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.nav_host_fragment)
        //Agregamos en el actionBar una flecha de retroceso en el Navigation controller haciendo la referencia en esta activity y agregando
        //el control de navegación contenido en el fragment container
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}