package com.nuevo.nuevoamanecer

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


class SelectActivity : AppCompatActivity() {

    private lateinit var spinnerNames: Spinner
    private lateinit var synchronizeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select)

        spinnerNames = findViewById(R.id.spinnerAlumno)
        synchronizeButton = findViewById(R.id.btnSincronizar)

        // Lista
        val names = mutableListOf<String>()

        // Referencia a firebase
        val databaseReference = FirebaseDatabase.getInstance().reference.child("images")


        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach { childSnapshot ->
                    // Nombre: Eduardo, Carlos, Cherry, Jerry, Eliezer
                    val name = childSnapshot.key
                    name?.let { names.add(it) }
                }

                val adapter = ArrayAdapter(this@SelectActivity, android.R.layout.simple_spinner_item, names)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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
            val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("user", selectedName)
                apply()
            }
        }
    }
}
