package com.nuevo.nuevoamanecer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class PersonalizacionSelec : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_selec)

        val btnConfirmar = findViewById(R.id.buttonConfirmarPersonalizacionSelec) as Button

        btnConfirmar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btnPersJuego1 = findViewById(R.id.imageButtonBuhoPersonalizacionSelec) as ImageButton

        btnPersJuego1.setOnClickListener{
            val intent = Intent(this, Juego1Personalizacion::class.java)
            startActivity(intent)
        }
    }
}