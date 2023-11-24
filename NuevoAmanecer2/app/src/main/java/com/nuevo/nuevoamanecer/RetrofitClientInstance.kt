package com.nuevo.nuevoamanecer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClientInstance {

    // Instancia de retrofit
    private var retrofit: Retrofit? = null
    //SI SE USA EL IP DE ELIEZER SE DEBE CAMBIAR LA URL A LA SIGUIENTE:
    //22. algo no me acuerdo
    private const val BASE_URL = "http://10.22.208.84:8000/"
    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
}
