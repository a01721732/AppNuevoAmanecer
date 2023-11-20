package com.nuevo.nuevoamanecer

import com.google.gson.annotations.SerializedName


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
