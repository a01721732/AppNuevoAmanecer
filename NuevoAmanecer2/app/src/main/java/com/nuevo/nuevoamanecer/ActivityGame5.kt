package com.nuevo.nuevoamanecer


import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ActivityGame5 : AppCompatActivity() {
    private var movingImage: ImageView? = null
    private var dX = 0f
    private var dY = 0f
    private var imagesMovedCount = 0
    private lateinit var btnRegresar: Button
    private lateinit var btnReset: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game5)

        //Pink = cherry
        //Green = lemon

        val draggableImages = listOf(
            R.id.cherry, R.id.lemon, R.id.orange,
            R.id.cherry1, R.id.lemon1, R.id.orange1,
            R.id.cherry2, R.id.lemon2, R.id.orange2,
            R.id.cherry3, R.id.lemon3, R.id.orange3
        )
        //Black = orange

        draggableImages.forEach { imageId ->
            val imageView = findViewById<ImageView>(imageId)
            setDraggable(imageView)
        }

        btnRegresar = findViewById(R.id.btnRegresarJuego3) as Button
        btnReset = findViewById(R.id.btnReset) as Button

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btnReset.setOnClickListener(){
            resetImages()
        }
    }

    private fun setDraggable(view: ImageView) {
        view.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    movingImage = view
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
                MotionEvent.ACTION_UP -> {
                    movingImage = null
                    checkIfAllImagesMoved()
                }
                else -> return@setOnTouchListener false
            }
            true
        }
    }

    private fun checkIfAllImagesMoved() {
        imagesMovedCount++
        if (imagesMovedCount == 12) { // Assuming you have 15 images in total
            showFinishDialog()
        }
    }

    private fun showFinishDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Acabaste?")
            .setMessage("¿Quieres volver a empezar?")
            .setPositiveButton("Sí") { _, _ ->
                resetImages()
            }
            .setNegativeButton("No") { _, _ ->
                // Continue with the game
            }
            .show()
    }

    private fun resetImages() {
        // Define the initial positions for each set of images.
        val initialPositions = listOf(
            Pair(150f, 200f),
            Pair(300f, 500f),
            Pair(600f, 200f),
            Pair(150f, 750f)
        )

        // Define the image views that need to be reset.
        val imageViewsToReset = listOf(
            R.id.cherry, R.id.lemon, R.id.orange,
            R.id.cherry1, R.id.lemon1, R.id.orange1,
            R.id.cherry2, R.id.lemon2, R.id.orange2,
            R.id.cherry3, R.id.lemon3, R.id.orange3
        )

        // Reset each image view to its initial position.
        imageViewsToReset.forEachIndexed { index, imageViewId ->
            // Calculate the position based on the index.
            val positionIndex = index / 3  // Assuming you have 3 images per set
            val (initialX, initialY) = initialPositions[positionIndex]

            findViewById<ImageView>(imageViewId).animate()
                .x(initialX)
                .y(initialY)
                .setDuration(0)
                .start()
        }

        // Reset the moved images count.
        imagesMovedCount = 0
    }

}


