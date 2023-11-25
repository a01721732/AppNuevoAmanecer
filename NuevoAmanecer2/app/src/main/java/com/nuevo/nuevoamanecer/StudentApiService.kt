package com.nuevo.nuevoamanecer

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface StudentApiService {

    // Rutas para el API
    @GET("getAlumnoPsicologo/{IdPsicologo}")
    fun getStudents(@Path("IdPsicologo") idPsicologo: Int): Call<List<Student?>>

    @POST("login")
    fun login(@Body credentials: HashMap<String, String>): Call<List<Psicologo>>

    @POST("updateNivelAlumno")
    fun updateStudentLevel(@Body requestData: HashMap<String, Int?>): Call<Void>

}

