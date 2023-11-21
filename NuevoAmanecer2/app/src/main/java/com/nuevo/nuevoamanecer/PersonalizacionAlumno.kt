package com.nuevo.nuevoamanecer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nuevo.nuevoamanecer.RetrofitClientInstance.retrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PersonalizacionAlumno : AppCompatActivity() {
    private var spinnerNames: Spinner? = null
    private var spinnerLevels: Spinner? = null
    private var synchronizeButton: Button? = null
    private var studentsList: List<Student?> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_alumno)
        spinnerNames = findViewById(R.id.dropdownAlumno)
        spinnerLevels = findViewById(R.id.dropdownNivel)
        synchronizeButton = findViewById(R.id.btnComenzarPersonalizacionAlumno)
        val btnPers = findViewById(R.id.btnPersonalizacionAlumno) as Button


        btnPers.setOnClickListener {
            val intent = Intent(this, PersonalizacionSelec::class.java)
            startActivity(intent)
        }

        val levels = listOf("1", "2", "3", "4")
        val adapterLevels = ArrayAdapter(this, R.layout.spinner_custom_item, levels)
        spinnerLevels?.adapter = adapterLevels

        val psychologistId = getPsychologistIdFromPreferences()
        loadStudents(psychologistId)

        synchronizeButton?.setOnClickListener {
            val selectedStudentId = getSelectedStudentId() ?: return@setOnClickListener
            val selectedLevel = getSelectedLevel()

            val requestData = hashMapOf("iIdAlumno" to selectedStudentId, "iNivel" to selectedLevel)
            val service = retrofitInstance?.create(StudentApiService::class.java)

            service?.updateStudentLevel(requestData)?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@PersonalizacionAlumno, "Nivel actualizado con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@PersonalizacionAlumno, "Error al actualizar el nivel", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@PersonalizacionAlumno, "Error en la red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


    private fun getSelectedStudentId(): Int? {
        val selectedStudent = spinnerNames?.selectedItem as? Student
        return selectedStudent?.id
    }

    private fun getSelectedLevel(): Int {
        return spinnerLevels?.selectedItem.toString().toIntOrNull() ?: -1
    }
    private fun getPsychologistIdFromPreferences(): Int {
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        return sharedPreferences.getInt("psicologoId", -1)
    }

    private fun loadStudents(idPsicologo: Int) {
        retrofitInstance?.create(StudentApiService::class.java)?.getStudents(idPsicologo)?.enqueue(object : Callback<List<Student?>> {
            override fun onResponse(call: Call<List<Student?>>, response: Response<List<Student?>>) {
                if (response.isSuccessful) {
                    val students = response.body() ?: emptyList()
                    val studentNames = students.mapNotNull { it?.name }
                    val adapter = ArrayAdapter(this@PersonalizacionAlumno, R.layout.spinner_custom_item, studentNames)
                    spinnerNames?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Student?>>, t: Throwable) {
                Log.e("PersonalizacionAlumno", "Error cargando estudiantes", t)
            }
        })
    }


}
