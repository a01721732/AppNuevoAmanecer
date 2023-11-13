package com.nuevo.nuevoamanecer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView

class ActivityGame2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game2)

        val btnRegresar = findViewById(R.id.btnRegresarJuego2) as Button

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val imagen = findViewById<ImageView>(R.id.imagen)

        imagen.setOnClickListener(object : View.OnClickListener {
            var rotated = false

            override fun onClick(v: View?) {
                val rotateAnimation = if (rotated) {
                    RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                } else {
                    RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
                }

                rotateAnimation.duration = 1000 // Duración de la animación en milisegundos
                imagen.startAnimation(rotateAnimation)
                rotated = !rotated
            }
        })
    }
}