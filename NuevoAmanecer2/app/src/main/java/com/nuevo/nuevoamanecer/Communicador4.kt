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

class Communicador4 : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private lateinit var sentenceArea: TextView

    private lateinit var resetButton: Button

    private lateinit var speakSentenceButton: Button

    private lateinit var btnComunicador1: ImageButton
    private lateinit var btnComunicador2: ImageButton
    private lateinit var btnComunicador3: ImageButton
    private lateinit var btnComunicador4: ImageButton
    private lateinit var btnComunicador5: ImageButton
    private lateinit var btnComunicador6: ImageButton
    private lateinit var btnComunicador7: ImageButton


    private lateinit var btnComunicador10: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicador4)

        val receivedVariable = intent.getStringExtra("key")

        tts = TextToSpeech(this, this)
        sentenceArea = findViewById(R.id.sentenceArea)

        resetButton = findViewById(R.id.resetButton)

        speakSentenceButton = findViewById(R.id.speakSentenceButton)

        btnComunicador1 = findViewById(R.id.comView31)
        btnComunicador2 = findViewById(R.id.comView32)
        btnComunicador3 = findViewById(R.id.comView33)
        btnComunicador4 = findViewById(R.id.comView34)
        btnComunicador5 = findViewById(R.id.comView37)
        btnComunicador6 = findViewById(R.id.comView36)
        btnComunicador7 = findViewById(R.id.comView35)

        btnComunicador10 = findViewById(R.id.comView38)

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
            addToSentence("comer")
        }
        btnComunicador2.setOnClickListener {
            addToSentence("dormir")
        }
        btnComunicador3.setOnClickListener {
            addToSentence("beber")
        }
        btnComunicador4.setOnClickListener {
            addToSentence("correr")
        }
        btnComunicador5.setOnClickListener {
            addToSentence("saltar en la pelota")
        }
        btnComunicador6.setOnClickListener {
            addToSentence("nadar")
        }


        btnComunicador10.setOnClickListener {
            val intent = Intent(this, Communicador5::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }



        btnComunicador7.setOnClickListener {
            val intent = Intent(this, Communicador3::class.java)
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