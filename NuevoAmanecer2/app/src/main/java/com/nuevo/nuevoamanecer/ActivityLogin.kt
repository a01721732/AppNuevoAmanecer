package com.nuevo.nuevoamanecer

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.nuevo.nuevoamanecer.RetrofitClientInstance

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
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val username = editTextUserName.text.toString()
            val password = editTextPassword.text.toString()
            performLogin(username, password)
        }
    }

    private fun performLogin(username: String, password: String) {

        //CAMBIAR ESTA PARTE CUANDO YA FUNCIONE CON EL API BIEN
        startActivity(Intent(this@ActivityLogin, PersonalizacionAlumno::class.java))
        return
        val service = RetrofitClientInstance.retrofitInstance?.create(StudentApiService::class.java)
        if (service != null) {
            val credentials = hashMapOf("sUserNamePsicologo" to username, "sPasswordPsicologo" to password)

            service.login(credentials).enqueue(object : Callback<List<Psicologo>> {
                override fun onResponse(call: Call<List<Psicologo>>, response: Response<List<Psicologo>>) {
                    if (response.isSuccessful && response.body() != null && response.body()!!.isNotEmpty()) {
                        val psicologo = response.body()!![0]
                        savePsicologoId(psicologo.id)
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
        val sharedPref = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("psicologoId", id)
            apply()
        }
    }
}
