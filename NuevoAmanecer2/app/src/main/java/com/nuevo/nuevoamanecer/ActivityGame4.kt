package com.nuevo.nuevoamanecer

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ActivityGame4 : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var sentenceArea: TextView
    private lateinit var gridLayout: GridLayout
    private lateinit var resetButton: Button
    private lateinit var leftArrowButton: Button
    private lateinit var rightArrowButton: Button
    private lateinit var speakSentenceButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game4)

        tts = TextToSpeech(this, this)
        sentenceArea = findViewById(R.id.sentenceArea)
        gridLayout = findViewById(R.id.gridLayout)
        resetButton = findViewById(R.id.resetButton)
        leftArrowButton = findViewById(R.id.leftArrowButton)
        rightArrowButton = findViewById(R.id.rightArrowButton)
        speakSentenceButton = findViewById(R.id.speakSentenceButton)


        populateGrid()
        setupButtons()
    }

    private fun populateGrid() {
        val actionItems = getActionItems()


        val screenWidth = resources.displayMetrics.densityDpi
        val imageMargin = (10 * resources.displayMetrics.density).toInt()
        val imageSize = 500

        actionItems.forEach { item ->
            val imageView = ImageView(this).apply {
                setImageResource(item.imageResId)
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = GridLayout.LayoutParams().apply {
                    width = imageSize
                    height = imageSize
                    setMargins(imageMargin, imageMargin, imageMargin, imageMargin)
                }
                setOnClickListener {
                    addToSentence(item.text)
                }
            }
            gridLayout.addView(imageView)
        }
    }



    private fun getActionItems(): List<ActionItem> {
        return listOf(
            ActionItem("I", R.drawable.image_me),
            ActionItem("You", R.drawable.image_you),
            ActionItem("Love", R.drawable.image_love),
            ActionItem("Eat", R.drawable.image_eat),
            ActionItem("Drink", R.drawable.image_drink),
            ActionItem("Go", R.drawable.image_go),
            ActionItem("Come", R.drawable.image_come),
            ActionItem("Help", R.drawable.image_help),
            ActionItem("Play", R.drawable.image_playing)
        )
    }


    private fun setupButtons() {
        resetButton.setOnClickListener { sentenceArea.text = "" }
        leftArrowButton.setOnClickListener {

        }
        rightArrowButton.setOnClickListener { }
        speakSentenceButton.setOnClickListener {
            tts.speak(sentenceArea.text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    private fun addToSentence(text: String) {
        sentenceArea.append("$text ")
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US //
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