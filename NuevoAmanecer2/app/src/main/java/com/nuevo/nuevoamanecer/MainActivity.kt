package com.nuevo.nuevoamanecer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnImg1 = findViewById(R.id.imageButton3) as ImageButton
        val btnImg2 = findViewById(R.id.imageButton4) as ImageButton
        val btnImg3 = findViewById(R.id.imageButton6) as ImageButton
        val btnAdmin = findViewById(R.id.buttonAdmin) as Button
        val btnImg4 = findViewById(R.id.imageButton7) as ImageButton
        val btnImg5 = findViewById(R.id.imageButton8) as ImageButton
        val btnImg6 = findViewById(R.id.imageButton9) as ImageButton



        btnImg1.setOnClickListener{
            val intent = Intent(this, ActivityGame1::class.java)
            startActivity(intent)
        }

        btnImg2.setOnClickListener{
            val intent = Intent(this, ActivityGame2::class.java)
            startActivity(intent)
        }

        btnImg3.setOnClickListener{
            val intent = Intent(this, ActivityGame3::class.java)
            startActivity(intent)
        }

        btnAdmin.setOnClickListener{
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }

        btnImg4.setOnClickListener{
            val intent = Intent(this, ActivityGame4::class.java)
            startActivity(intent)
        }

        btnImg5.setOnClickListener{
            val intent = Intent(this, ActivityGame5::class.java)
            startActivity(intent)
        }

        btnImg6.setOnClickListener{
            val intent = Intent(this, CaraDePapa::class.java)
            startActivity(intent)
        }

    }
}