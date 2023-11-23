package com.nuevo.nuevoamanecer

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

class PersonalizacionSelec : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val storageReference = FirebaseStorage.getInstance().reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personalizacion_selec)



        val btnConfirmar = findViewById<Button>(R.id.buttonConfirmarPersonalizacionSelec)
        val btnPersJuego1 = findViewById<ImageButton>(R.id.imageButtonBuhoPersonalizacionSelec)

        btnConfirmar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnPersJuego1.setOnClickListener {
            // Abre la galeria
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAGE_PICK_CODE)
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val imageUri: Uri? = data?.data
            imageUri?.let {
                handleImageSelected(it)
            }
        }
    }

    private fun handleImageSelected(imageUri: Uri) {

        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE) //Obtener el nombre del usuario de sharedprefs
        val personName = sharedPref.getString("user", "DefaultName")

        //val childName = "Carlos" // Cambiar esto con shared preferences para el nombre el nino
        val imageName = "${UUID.randomUUID()}.jpg"

        // Ensure the destination path exists in Firebase Storage
        val childReference: StorageReference = storageReference.child(personName.toString())
        childReference.child(imageName).putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                val imageUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString()
                saveImageUrlToDatabase(personName.toString(), imageUrl)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Image upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun saveImageUrlToDatabase(childName: String, imageUrl: String) {
        // Save the image URL under "pic" inside the "images" node in the Firebase Realtime Database
        databaseReference.child("images").child(childName).child("foto").setValue(imageUrl)
    }

}