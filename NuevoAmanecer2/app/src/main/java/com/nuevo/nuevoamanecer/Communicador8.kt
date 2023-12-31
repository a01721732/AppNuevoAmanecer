package com.nuevo.nuevoamanecer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Locale

class Communicador8 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var sentenceArea: TextView
    private lateinit var resetButton: Button
    private lateinit var speakSentenceButton: Button
    private lateinit var regresarBtn: Button

    private lateinit var personName: String
    private lateinit var imageFather: ImageView
    private lateinit var imageMother: ImageView
    private lateinit var imageBrother: ImageView
    private lateinit var imageSister: ImageView
    private val familyMembers = listOf("father", "mother", "brother", "sister")
    private val imageViewsMap = mapOf(
        "father" to R.id.imageFather,
        "mother" to R.id.imageMother,
        "brother" to R.id.imageBrother,
        "sister" to R.id.imageSister
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicador8)

        // Fetch personName from SharedPreferences
        val sharedPref = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        personName = sharedPref.getString("user", "DefaultName") ?: "DefaultName"

        tts = TextToSpeech(this, this)
        sentenceArea = findViewById(R.id.sentenceArea)

        resetButton = findViewById(R.id.resetButton)
        speakSentenceButton = findViewById(R.id.speakSentenceButton)

        resetButton.setOnClickListener { sentenceArea.text = "" }
        speakSentenceButton.setOnClickListener {
            tts.speak(sentenceArea.text, TextToSpeech.QUEUE_FLUSH, null, null)
            Log.d("TTS", "Speaking sentence: ${sentenceArea.text}")
        }

        regresarBtn = findViewById(R.id.btnRegresarJuego1)

        regresarBtn.setOnClickListener{
            val intent = Intent(this, Communicador1::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }

        /*
        fetchImageForFamilyMember("father")
        fetchImageForFamilyMember("mother")
        fetchImageForFamilyMember("brother")
        fetchImageForFamilyMember("sister")
        */


        imageFather = findViewById<ImageView>(R.id.imageFather)
        imageMother = findViewById<ImageView>(R.id.imageMother)
        imageBrother = findViewById<ImageView>(R.id.imageBrother)
        imageSister = findViewById<ImageView>(R.id.imageSister)

        setupImageViewClickListeners()

        familyMembers.forEach { familyMember ->
            fetchImageForFamilyMember(familyMember)
        }
    }

    private fun fetchImageForFamilyMember(familyMember: String) {
        val path = "images/$personName/family/$familyMember"
        val databaseReference = FirebaseDatabase.getInstance().getReference(path)

        databaseReference.orderByChild("timestamp").limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Checar
                    val imageUrl = snapshot.children.firstOrNull()?.child("url")?.getValue(String::class.java)
                    if (imageUrl != null) {
                        // Si existe, descargar la imagen y setearla
                        downloadAndSetImage(imageUrl, familyMember)
                    } else {
                        // No hay URL de imagen
                        setDefaultImage(familyMember)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                    Log.e("Firebase", "Database error: $error")
                    setDefaultImage(familyMember)
                }
            })
    }


    private fun resetToDefaultImages() {
        setDefaultImage("father")
        setDefaultImage("mother")
        setDefaultImage("brother")
        setDefaultImage("sister")
    }

    private fun downloadAndSetImage(imageUrl: String, familyMember: String) {
        val imageViewId = imageViewsMap[familyMember] ?: return
        val imageView = findViewById<ImageView>(imageViewId)

        Glide.with(this)
            .load(imageUrl)
            .error(getDefaultDrawableForFamilyMember(familyMember)) // Fallback to default image if Glide fails
            .into(imageView)
    }



    private fun getDefaultDrawableForFamilyMember(familyMember: String): Int {
        return when (familyMember) {
            "father" -> R.drawable.fathericon
            "mother" -> R.drawable.mothericon
            "brother" -> R.drawable.brothericon
            "sister" -> R.drawable.sistericon

            else -> R.drawable.family_icon // Default
        }
    }


    private fun setDefaultImage(familyMember: String) {
        val imageViewId = imageViewsMap[familyMember]
        val imageView = imageViewId?.let { findViewById<ImageView>(it) }
        val defaultDrawableId = getDefaultDrawableForFamilyMember(familyMember)
        imageView?.setImageResource(defaultDrawableId)
    }

    private fun setupImageViewClickListeners() {
        imageFather.setOnClickListener { addToSentence("padre") }
        imageMother.setOnClickListener { addToSentence("madre") }
        imageBrother.setOnClickListener { addToSentence("hermano") }
        imageSister.setOnClickListener { addToSentence("hermana") }
    }

    private fun addToSentence(text: String) {
        sentenceArea.append("$text ")
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        Log.d("TTS", "Added $text to sentence")
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            //tts.language = Locale.US //
            tts.setLanguage(Locale("es", "ES"))
        }
    }

    override fun onDestroy() {
        if (tts.isSpeaking) {
            tts.stop()
        }
        tts.shutdown()
        super.onDestroy()
    }


}
