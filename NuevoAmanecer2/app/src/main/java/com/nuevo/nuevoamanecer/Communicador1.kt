package com.nuevo.nuevoamanecer

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import android.widget.ImageButton
class Communicador1 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var sentenceArea: TextView
    private lateinit var resetButton: Button

    private lateinit var speakSentenceButton: Button

    private lateinit var btnComunicador1: ImageButton
    private lateinit var btnComunicador2: ImageButton
    private lateinit var btnComunicador3: ImageButton
    private lateinit var btnComunicador4: ImageButton
    private lateinit var btnRegresar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicador1)

        val receivedVariable = intent.getStringExtra("key")


        tts = TextToSpeech(this, this)
        sentenceArea = findViewById(R.id.sentenceArea)

        resetButton = findViewById(R.id.resetButton)

        speakSentenceButton = findViewById(R.id.speakSentenceButton)

        btnComunicador1 = findViewById(R.id.comView1)
        btnComunicador2 = findViewById(R.id.comView2)
        btnComunicador3 = findViewById(R.id.comView3)
        btnComunicador4 = findViewById(R.id.comView4)
        btnRegresar = findViewById(R.id.btnRegresarJuego1) as Button

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }




        sentenceArea.text = receivedVariable



        setupButtons()
    }









    private fun setupButtons() {
        resetButton.setOnClickListener { sentenceArea.text = "" }

        speakSentenceButton.setOnClickListener {
            tts.speak(sentenceArea.text, TextToSpeech.QUEUE_FLUSH, null, null)
            Log.d("TTS", "Speaking sentence: ${sentenceArea.text}")
        }
        btnComunicador1.setOnClickListener {
            val intent = Intent(this, Communicador6::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }
        btnComunicador2.setOnClickListener {
            val intent = Intent(this, Communicador3::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }
        btnComunicador3.setOnClickListener {
            val intent = Intent(this, Communicador2::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }
        btnComunicador4.setOnClickListener {
            val intent = Intent(this, Communicador8::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }



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