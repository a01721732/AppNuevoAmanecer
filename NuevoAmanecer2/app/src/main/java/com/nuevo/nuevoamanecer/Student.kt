package com.nuevo.nuevoamanecer

import com.google.gson.annotations.SerializedName


//Clase de datos para el estudiante, se usa SerializedName para que el nombre de las variables coincida con el de la respuesta del API
data class Student(
    @SerializedName("iIdAlumno")
    val id: Int = 0,

    @SerializedName("sNombre")
    val name: String? = null,

    @SerializedName("sApellido")
    val lastName: String? = null,

    @SerializedName("iIdNivelCognitivo")
    val cognitiveLevel: Int = 0
)
