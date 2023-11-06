package com.nuevo.nuevoamanecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PersonalizacionAlumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_alumno)




        val btnRegresar = findViewById(R.id.btnRegresarPersonalizacionAlumno) as Button

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnComenzar = findViewById(R.id.btnComenzarPersonalizacionAlumno) as Button

        btnComenzar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnPersonalizacion = findViewById(R.id.btnPersonalizacionAlumno) as Button

        btnPersonalizacion.setOnClickListener{
            val intent = Intent(this, PersonalizacionSelec::class.java)
            startActivity(intent)
        }
    }
}