package com.nuevo.nuevoamanecer

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

        // Use the activity result API for image picking
        val imagePicker =
            registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
                uri?.let {
                    handleImageSelected(it)
                }
            }

        btnPersJuego1.setOnClickListener {
            // Start the image picker
            imagePicker.launch("image/*")
        }
    }

    private fun handleImageSelected(imageUri: Uri) {
        // Generate a unique name for the image based on the child's name
        val childName = "images" // Replace with the actual child's name
        val imageName = "$childName/${UUID.randomUUID()}.jpg"

        // Upload the image to Firebase Storage
        val imageRef = storageReference.child(imageName)
        imageRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                // Image upload success
                Toast.makeText(this, "Image uploaded successfully", Toast.LENGTH_SHORT)
                    .show()

                // Now, you may want to store the image URL in the Firebase Realtime Database
                val imageUrl = taskSnapshot.metadata?.reference?.downloadUrl.toString()
                saveImageUrlToDatabase(childName, imageUrl)
            }
            .addOnFailureListener { e ->
                // Handle unsuccessful uploads
                Toast.makeText(this, "Image upload failed: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun saveImageUrlToDatabase(childName: String, imageUrl: String) {
        // Save the image URL under the child's name in the Firebase Realtime Database
        databaseReference.child(childName).setValue(imageUrl)
    }


}