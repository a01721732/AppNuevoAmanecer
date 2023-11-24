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

class Communicador6 : AppCompatActivity(), TextToSpeech.OnInitListener {

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
    private lateinit var btnComunicador8: ImageButton



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_communicador6)

        val receivedVariable = intent.getStringExtra("key")

        tts = TextToSpeech(this, this)
        sentenceArea = findViewById(R.id.sentenceArea)

        resetButton = findViewById(R.id.resetButton)
        speakSentenceButton = findViewById(R.id.speakSentenceButton)

        btnComunicador1 = findViewById(R.id.comView51)
        btnComunicador2 = findViewById(R.id.comView52)
        btnComunicador3 = findViewById(R.id.comView53)
        btnComunicador4 = findViewById(R.id.comView54)
        btnComunicador5 = findViewById(R.id.comView55)
        btnComunicador6 = findViewById(R.id.comView56)
        btnComunicador7 = findViewById(R.id.comView57)
        btnComunicador8 = findViewById(R.id.comView58)

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
            addToSentence("jugetes")
        }
        btnComunicador2.setOnClickListener {
            addToSentence("burbujas")
        }
        btnComunicador3.setOnClickListener {
            addToSentence("pelota")
        }
        btnComunicador4.setOnClickListener {
            addToSentence("plastilina")
        }
        btnComunicador7.setOnClickListener {
            addToSentence("lápices de colores")
        }
        btnComunicador6.setOnClickListener {
            addToSentence("pinturas")
        }
        btnComunicador8.setOnClickListener {
            val intent = Intent(this, Communicador7::class.java)
            intent.putExtra("key", sentenceArea.text.toString())
            startActivity(intent)
        }
        btnComunicador5.setOnClickListener {
            val intent = Intent(this, Communicador1::class.java)
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