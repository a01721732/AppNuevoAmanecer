package com.nuevo.nuevoamanecer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnImg1 = findViewById(R.id.imageButton3) as ImageButton
        val tigreImg = findViewById(R.id.imageButton6) as ImageButton
        val adminBtn = findViewById(R.id.button) as Button

        adminBtn.setOnClickListener{
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
        }
        btnImg1.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        tigreImg.setOnClickListener{
            val intent = Intent(this, DragDropActivity::class.java)
            startActivity(intent)
        }




    }
}