package com.nuevo.nuevoamanecer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.widget.Button
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val cognitiveLevel = sharedPref.getInt("cognitiveLevel", 0)
        Log.d("MainActivity", "Cognitive level: $cognitiveLevel")

        // Cambiar layout dependiendo del nivel cognitivo
        when (cognitiveLevel) {
            0 -> setContentView(R.layout.activity_main_grayed_out)
            else -> setContentView(R.layout.activity_main)
        }

        // Initialize buttons
        val btnImg1 = findViewById<ImageButton>(R.id.imageButton3)
        val btnImg2 = findViewById<ImageButton>(R.id.imageButton4)
        val btnImg3 = findViewById<ImageButton>(R.id.imageButton6)
        val btnImg4 = findViewById<ImageButton>(R.id.imageButton7)
        val btnImg5 = findViewById<ImageButton>(R.id.imageButton8)
        val btnImg6 = findViewById<ImageButton>(R.id.imageButton9)
        val btnAdmin = findViewById<Button>(R.id.buttonAdmin)
        val textBienvenido = findViewById<TextView>(R.id.textViewBienvenido)

        btnAdmin.setOnClickListener {

            startActivity(Intent(this, ActivityLogin::class.java))
        }

        if (cognitiveLevel > 0) {
            setupButtonInteractions(btnImg1, ActivityGame1::class.java, cognitiveLevel >= 1)
            setupButtonInteractions(btnImg2, ActivityGame2::class.java, cognitiveLevel >= 1)
            setupButtonInteractions(btnImg3, ActivityGame3::class.java, cognitiveLevel >= 2)
            setupButtonInteractions(btnImg4, Communicador1::class.java, cognitiveLevel >= 2)
            setupButtonInteractions(btnImg5, ActivityGame5::class.java, cognitiveLevel >= 3)
            setupButtonInteractions(btnImg6, CaraDePapa::class.java, cognitiveLevel >= 3)
            textBienvenido.text = "Bienvenido " + sharedPref.getString("user", "DefaultName")
        }
        else{
            btnAdmin.alpha = 1.0f
        }

    }


    private fun setupButtonInteractions(button: ImageButton, activityClass: Class<*>, isEnabled: Boolean) {
        if (isEnabled) {
            button.alpha = 1.0f
            button.setOnClickListener {
                startActivity(Intent(this, activityClass))
            }
        } else {
            button.isEnabled = true
            button.alpha = 0.1f
            button.setOnClickListener {
                showAccessDeniedDialog()
            }
        }
    }

    private fun showAccessDeniedDialog() {
        AlertDialog.Builder(this)
            .setTitle("Acceso Denegado")
            .setMessage("No tienes acceso a este juego")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

}
