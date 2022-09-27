package com.alex.bebidasapp.core

//Es para el uso de corrutinas similar al uso de callbacks con interfaces pero esto es mas facil de implementar
sealed class Resource<out T> {
    //Se definen tres estados cuando se solicita un recurso
    class Loading<out T> : Resource<T>() //No retorna nada solo avisa de que esta cargando algo
    data class Success<out T>(val data: T) : Resource<T>() //en el succes si, por que le vamos a pasar información y por lo tanto debe ser un dataclass
    data class Failure<out T>(val exception: Exception) : Resource<T>() //en el failure tambien pero aqui se le pasan excepciones o errores
}

