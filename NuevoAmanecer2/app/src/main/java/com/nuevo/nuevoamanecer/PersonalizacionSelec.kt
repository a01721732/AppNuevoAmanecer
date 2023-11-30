package com.nuevo.nuevoamanecer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import java.util.UUID

class PersonalizacionSelec : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val storageReference = FirebaseStorage.getInstance().reference
    private val client = OkHttpClient()
    private var lastClickedButton: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_selec)



        val btnConfirmar = findViewById<Button>(R.id.buttonConfirmarPersonalizacionSelec)
        val btnPersJuego1 = findViewById<ImageButton>(R.id.imageButtonPuzzle)
        val btnPersFamilia = findViewById<ImageButton>(R.id.imageFather)
        val btnMother = findViewById<ImageButton>(R.id.imageMother)
        val btnBrother = findViewById<ImageButton>(R.id.imageBrother)
        val btnSister = findViewById<ImageButton>(R.id.imageSister)
        val btnRegresar = findViewById<Button>(R.id.btnRegresarJuego1)
        val textAlumno = findViewById<TextView>(R.id.textPersonalizacionAlumno)

        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val personName = sharedPref.getString("user", "DefaultName") ?: "DefaultName"
        textAlumno.text = "Personalizando para: $personName"

        btnConfirmar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        btnRegresar.setOnClickListener {
            val intent = Intent(this, PersonalizacionAlumno::class.java)
            startActivity(intent)
        }
        btnPersJuego1.setOnClickListener {
            lastClickedButton = BUTTON_TYPE_PUZZLE
            startImagePicker()
        }

        btnPersFamilia.setOnClickListener {
            lastClickedButton = BUTTON_TYPE_FATHER
            startImagePicker()
        }

        btnMother.setOnClickListener {
            lastClickedButton = BUTTON_TYPE_MOTHER
            startImagePicker()
        }

        btnBrother.setOnClickListener {
            lastClickedButton = BUTTON_TYPE_BROTHER
            startImagePicker()
        }

        btnSister.setOnClickListener {
            lastClickedButton = BUTTON_TYPE_SISTER
            startImagePicker()
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val BUTTON_TYPE_PUZZLE = "puzzle"
        private const val BUTTON_TYPE_FATHER = "father"
        private const val BUTTON_TYPE_MOTHER = "mother"
        private const val BUTTON_TYPE_BROTHER = "brother"
        private const val BUTTON_TYPE_SISTER = "sister"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val imageUri: Uri? = data?.data
            imageUri?.let {
                handleImageSelected(it,lastClickedButton)
            }
        }
    }


    //Esta funcion hace uso de la API de imgur para subir la imagen a un servidor
    //La idea es que se suben las fotos locales de android, y se obtiene un URL
    //Teniendo el URL, se manda el URL y el timestamp a la base de datos de firebase
    //Para en otros juegos poder bajar la foto con el URL
    private fun handleImageSelected(imageUri: Uri, gameType: String) {

        //Obtener el nombre del usuario de sharedprefs
        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val personName = sharedPref.getString("user", "DefaultName") ?: "DefaultName"

        // Obtener el contenido de la imagen y el tipo de la imagen
        val mimeType = contentResolver.getType(imageUri)
        val imageStream = contentResolver.openInputStream(imageUri) ?: return

        // Crear el cuerpo de la peticion
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "filename", RequestBody.create(mimeType?.toMediaTypeOrNull(), imageStream.readBytes()))
            .build()

        // Crear y mandar la peticion
        val request = Request.Builder()
            .url("https://api.imgur.com/3/image")
            .header("Authorization", "Client-ID 360ac22aafbb2de")
            .post(requestBody)
            .build()


        //Manejar la peticion asincronicamente con enqueue en otro hilo
        // El callback maneja la respuesta
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                response.close()

                // Si la respuesta es exitosa, obtener el URL de la imagen y guardarlo en la base de datos
                if (response.isSuccessful && responseBody.isNotEmpty()) {
                    val json = JSONObject(responseBody)
                    val imageUrl = json.getJSONObject("data").getString("link")

                    runOnUiThread {
                        Toast.makeText(this@PersonalizacionSelec, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                        when (gameType) {
                            BUTTON_TYPE_PUZZLE -> saveImageUrlToDatabase(personName, "puzzle", imageUrl)
                            else -> saveImageUrlToDatabase(personName, "family/$gameType", imageUrl)
                        }
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(this@PersonalizacionSelec, "Upload failed: ${response.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@PersonalizacionSelec, "Image upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun startImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
    }

    private fun saveImageUrlToDatabase(childName: String, gameType: String, imageUrl: String) {
        // Generar clave
        val timestamp = System.currentTimeMillis()

        // Crear un objeto con la informacion de la imagen
        val imageInfo = mapOf("timestamp" to timestamp, "url" to imageUrl)

        //  Guardar la informacion en la base de datos
        databaseReference.child("images").child(childName).child(gameType).push().setValue(imageInfo)
    }




}