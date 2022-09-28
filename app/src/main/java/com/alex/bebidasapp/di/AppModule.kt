package com.alex.bebidasapp.di

import android.content.Context
import androidx.room.Room
import com.alex.bebidasapp.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Aqui definimos las instancias usamos en la aplicacion en este modulo
@Module
/*Indicamos con esta anotación que todas las instancias que definamos aqui van a estar atachadas en la ejecución de application
//es decir, por ejemplo si creamos una instancia de room aqui le indicamos que la instacia va estar activa hasta que la aplicación muera
//pero si la instancia de se definiera en el FragmentComponent la instancia va a morir cuando se salga del fragment o pase a un activity haciendo que borre las instancias creadas, por lo que
// no va a persistir esa instancia durante toda la aplicación por eso definimos SingletonComponent que hace que se inyecte las instancias dentro
//del componente Application por lo que vivirá la instancia hasta que uno cierre la aplicación*/
@InstallIn(SingletonComponent::class)
object AppModule {
    /*con el anotador @ApplicationContext no necesitamos pasarle un contexto ya lo hace hilt por nosotros,
    //con la anotación provides estamos diciendo que vamos a proveer una instancia, ahora cuando nosotros llamemos a la instancia de room
    //puede que se creen varias instancias, es decir que aunque se haya creado desde el contexto de la aplicación una instancia de room
    //cuando fue llamado por primera vez y se vuelva a llamar esa instancia nos creara otro haciendo que haya duplicidad de datos,
    //o que se muestren datos que no son, etc. pero en ningun momento indicamos que esa instancia es unica es por eso que le debemos poner la anotación
    //Singleton para utilizar solo una instancia y que no nos este creando otras instancia*/

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context:Context)=
        Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "drinks.db"
        ).build()

    //Aqui definimos la instancia que vamos a proveer de nuestro dao en room, lo cual
    //necesitará unicamente para funcionar de nuestra appDataBase para proveer el dao de bebidas
    //y como pusimos arriba la instancia de room pues ya sabe hitl que lo va a tomar de ahi para
    //hacer funcionar nuestro AppDataBase por que extiende de room
    @Singleton
    @Provides
    fun provideDrinkDao(db: AppDataBase) = db.drinkDao()
}