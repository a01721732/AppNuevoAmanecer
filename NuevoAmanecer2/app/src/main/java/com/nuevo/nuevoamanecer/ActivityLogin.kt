package com.nuevo.nuevoamanecer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {

    private lateinit var editTextUserName: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUserName = findViewById(R.id.editTextUserName)
        editTextPassword = findViewById(R.id.editTextPassword)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarLogin)
        val btnLogin = findViewById<Button>(R.id.btnLoginLogin)

        btnRegresar.setOnClickListener {
            Log.d("ActivityLogin", "Try to show login activity")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val username = editTextUserName.text.toString()
            val password = editTextPassword.text.toString()
            performLogin(username, password)
        }
    }

    private fun performLogin(username: String, password: String) {

        //CAMBIAR ESTA PARTE CUANDO YA FUNCIONE CON EL API BIEN
       // startActivity(Intent(this@ActivityLogin, PersonalizacionAlumno::class.java))
        //return

        // Creacion de instancia de cliente de retrofit
        val service = RetrofitClientInstance.retrofitInstance?.create(EstudianteApiService::class.java)  //El .create crea una implementacion de StudentApiService usando la instancia de retrofit
        if (service != null) { //Checar que no sea null para evitar problemas
            val credentials = hashMapOf("sUserNamePsicologo" to username, "sPasswordPsicologo" to password) //Mapa con las credenciales

            //Llamada al servicio de login en el interfaz de StudentApiService, hace un POST
            //La parte de enqueue hace una llamada asincrona, se ejecuta en un hilo diferente al principal
            //Regresa la respuesta en una implementacion anonima del interfaz de Callback
            //El tipo generico de list<Psicologo> es el tipo de la respuesta que se espera.
            //Psicologo es un data class que se encuentra en los archivos
            service.login(credentials).enqueue(object : Callback<List<Psicologo>> {
                override fun onResponse(call: Call<List<Psicologo>>, response: Response<List<Psicologo>>) {
                    // Checar que la respuesta este correcta y que no sea null
                    if (response.isSuccessful && response.body() != null && response.body()!!.isNotEmpty()) {
                        val psicologo = response.body()!![0]
                        savePsicologoId(psicologo.id)
                        savePsicologoName(psicologo.nombre)
                        startActivity(Intent(this@ActivityLogin, PersonalizacionAlumno::class.java))
                    } else {
                        Toast.makeText(this@ActivityLogin, "Login failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Psicologo>>, t: Throwable) {
                    Toast.makeText(this@ActivityLogin, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, "Error initializing network service", Toast.LENGTH_SHORT).show()
        }
    }


    private fun savePsicologoId(id: Int) {

        // Guardar el id del psicologo en shared preferences
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("psicologoId", id)
            apply()
        }
    }

    private fun savePsicologoName(name: String) {

        // Guardar el id del psicologo en shared preferences
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("psicologoName", name)
            apply()
        }
    }
}
