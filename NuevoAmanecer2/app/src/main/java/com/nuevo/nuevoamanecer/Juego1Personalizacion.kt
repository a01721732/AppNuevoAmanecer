package com.nuevo.nuevoamanecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Juego1Personalizacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego1_personalizacion)


        val btnConfirmar = findViewById(R.id.btnConfirmarPersonalizacionJuego1) as Button

        btnConfirmar.setOnClickListener{
            val intent = Intent(this, PersonalizacionSelec::class.java)
            startActivity(intent)
        }
    }
}