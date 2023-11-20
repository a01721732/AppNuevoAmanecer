package com.nuevo.nuevoamanecer

import com.google.gson.annotations.SerializedName

data class Psicologo(
    @SerializedName("iIdPsicologo")
    val id: Int,

    @SerializedName("sNombrePsicologo")
    val nombre: String
)
