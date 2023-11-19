package com.nuevo.nuevoamanecer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


class PersonalizacionAlumno : AppCompatActivity() {

    private lateinit var spinnerNames: Spinner
    private lateinit var synchronizeButton: Button
    private lateinit var spinnerLevels: Spinner



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_alumno)

        val btnPers = findViewById(R.id.btnPersonalizacionAlumno) as Button
        val btnRegresar = findViewById(R.id.btnRegresarPersonalizacionAlumno) as Button

        btnRegresar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPers.setOnClickListener {
            val intent = Intent(this, PersonalizacionSelec::class.java)
            startActivity(intent)
        }

        spinnerNames = findViewById(R.id.dropdownAlumno)
        synchronizeButton = findViewById(R.id.btnComenzarPersonalizacionAlumno)
        spinnerLevels = findViewById(R.id.dropdownNivel)

        // Lista
        val names = mutableListOf<String>()
        val levels = listOf("1", "2", "3", "4")

        val adapterLevels = ArrayAdapter(this, R.layout.spinner_custom_item, levels)
        adapterLevels.setDropDownViewResource(R.layout.spinner_custom_item)

        spinnerLevels.adapter = adapterLevels

        // Referencia a firebase
        val databaseReference = FirebaseDatabase.getInstance().reference.child("images")


        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { childSnapshot ->
                    // Nombre: Eduardo, Carlos, Cherry, Jerry, Eliezer
                    val name = childSnapshot.key
                    name?.let { names.add(it) }
                }

                val adapter = ArrayAdapter(this@PersonalizacionAlumno, R.layout.spinner_custom_item, names)
                adapter.setDropDownViewResource(R.layout.spinner_custom_item)

                spinnerNames.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Error
            }
        })

        synchronizeButton.setOnClickListener {
            // Sacar nombre
            val selectedName = spinnerNames.selectedItem.toString()
            //  Guardar nombre en sharedprefs
            val sharedPref = this.getSharedPreferences("AppPrefs", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("user", selectedName)
                apply()
            }
            Log.i("SelectActivity", "Saved $selectedName in SharedPreferences")

        }




    }
}
