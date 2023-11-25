package com.nuevo.nuevoamanecer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        val btnRegresar = findViewById(R.id.btnRegresarPersonalizacionAlumno) as Button

        val levels = listOf("0","1", "2", "3", "4")
        val adapterLevels = ArrayAdapter(this, R.layout.spinner_custom_item, levels)
        spinnerLevels?.adapter = adapterLevels

        spinnerNames?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedStudent = studentsList[position]
                val currentLevel = selectedStudent?.cognitiveLevel ?: 0
                val levelPosition = levels.indexOf(currentLevel.toString())
                spinnerLevels?.setSelection(levelPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Hacer nada
            }
        }



        btnPers.setOnClickListener {
            val intent = Intent(this, PersonalizacionSelec::class.java)
            startActivity(intent)
        }



        val psychologistId = getPsychologistIdFromPreferences()
        loadStudents(psychologistId)

        btnRegresar.setOnClickListener {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
        }

        synchronizeButton?.setOnClickListener {
            Log.d("PersonalizacionAlumno", "Try to synchronize student level")

            //ESTA PARTE DEBERIA ESTAR ABAJO

            //ya no esta
            // ESTA PARTE DEBERIA ESTAR ABAJO

            val selectedStudentId = getSelectedStudentId()
            val selectedLevel = getSelectedLevel()
            Log.d("Data", "selected student id: $selectedStudentId (${selectedStudentId?.javaClass?.simpleName}) selected level: $selectedLevel (${selectedLevel.javaClass.simpleName})")

            val requestData = hashMapOf("iIdAlumno" to selectedStudentId, "iNivel" to selectedLevel)
            val service = retrofitInstance?.create(StudentApiService::class.java)

            Log.d("PersonalizacionAlumno", "Try to synchronize student level: ${requestData.get("iIdAlumno")}, ${requestData.get("iNivel")}")

            service?.updateStudentLevel(requestData)?.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@PersonalizacionAlumno, "Nivel actualizado con éxito", Toast.LENGTH_SHORT).show()


                        //ESPACIO ABIERTO PARA LA PARTE DE SHARED PREREFERENCES
                        //AQUI DEBERIA ESTAR EN VEZ DE ESTAR ARRIBA EN LA FUNCION
                        //MOVER CUANDO YA FUNCIONE CON EL API BIEN

                        var selectedCognitiveLevel = getSelectedLevel()
                        Log.d("Data", "selected cognitive level: $selectedCognitiveLevel (${selectedCognitiveLevel?.javaClass?.simpleName})")
                        if (selectedCognitiveLevel == null) {
                            selectedCognitiveLevel = -1
                        }

                            val selected = spinnerNames?.selectedItem as? String
                            val selectedStudent = studentsList.find { it?.name == selected }
                            val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                            Log.d("StudentUpdate", "Student name: ${selectedStudent?.name} student id: ${selectedStudent?.id}) cognitive level: $selectedCognitiveLevel")
                            with(sharedPref.edit()) {
                                putInt("cognitiveLevel", selectedCognitiveLevel)
                                putInt("studentId", selectedStudent?.id ?: -1)
                                putString("user", selectedStudent?.name ?: "DefaultName")
                                apply()
                            }

                        //AQUI DEBERIA ESTAR EN VEZ DE ESTAR ARRIBA EN LA FUNCION
                        showUpdateSuccessDialog(selectedStudent?.name, selectedLevel)




                    } else {
                        Toast.makeText(this@PersonalizacionAlumno, "Error al actualizar el nivel", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@PersonalizacionAlumno, "Error en la red: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
           // val intent = Intent(this, MainActivity::class.java)
            //startActivity(intent)

        }
    }


    private fun getSelectedStudentId(): Int? {
        val selectedStudentName = spinnerNames?.selectedItem as? String
        val selectedStudent = studentsList.find { it?.name == selectedStudentName }
        Log.d("Student select", "Student name is ${selectedStudent?.name} student id is ${selectedStudent?.id}")
        return selectedStudent?.id
    }




    private fun updateCognitiveLevel(): Int? {
        // Get the name of the selected student from spinnerNames
        val selectedStudentName = spinnerNames?.selectedItem as? String
        // Find the student in the studentsList that matches the selected name
        val selectedStudent = studentsList.find { it?.name == selectedStudentName }
        // Return the cognitive level of the found student
        return selectedStudent?.cognitiveLevel
    }



    private fun getSelectedLevel(): Int {
        return spinnerLevels?.selectedItem.toString().toIntOrNull() ?: -1
    }

    private fun getSelectedName(): String {
        return spinnerNames?.selectedItem as String
    }

    private fun getPsychologistIdFromPreferences(): Int {
        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        return sharedPreferences.getInt("psicologoId", -1)
    }


    private fun showUpdateSuccessDialog(studentName: String?, cognitiveLevel: Int) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Actualización Exitosa")
        dialogBuilder.setMessage("Datos actualizados para $studentName:\nNivel Cognitivo: $cognitiveLevel")
        dialogBuilder.setPositiveButton("Regresar al Login") { dialog, _ ->
            dialog.dismiss()
            startActivity(Intent(this, ActivityLogin::class.java))
        }
        dialogBuilder.setNegativeButton("Cerrar") { dialog, _ ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun loadStudents(idPsicologo: Int) {
        retrofitInstance?.create(StudentApiService::class.java)?.getStudents(idPsicologo)?.enqueue(object : Callback<List<Student?>> {
            override fun onResponse(call: Call<List<Student?>>, response: Response<List<Student?>>) {
                if (response.isSuccessful) {
                    val students = response.body() ?: emptyList()
                    val studentNames = students.mapNotNull { it?.name }
                    studentsList = students

                    // Set the adapter for spinnerNames with student names
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