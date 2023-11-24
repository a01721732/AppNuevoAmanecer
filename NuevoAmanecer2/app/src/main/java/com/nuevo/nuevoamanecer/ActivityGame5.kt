package com.nuevo.nuevoamanecer


import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ActivityGame5 : AppCompatActivity() {
    private var movingImage: ImageView? = null
    private var dX = 0f
    private var dY = 0f
    private var imagesMovedCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game5)

        setDraggable(findViewById(R.id.yellowSquare))
        setDraggable(findViewById(R.id.redSquare))
        setDraggable(findViewById(R.id.orangeSquare))
        setDraggable(findViewById(R.id.cherry))
        setDraggable(findViewById(R.id.lemon))
        setDraggable(findViewById(R.id.orange))
        setDraggable(findViewById(R.id.cherry1))
        setDraggable(findViewById(R.id.lemon1))
        setDraggable(findViewById(R.id.orange1))
        setDraggable(findViewById(R.id.cherry2))
        setDraggable(findViewById(R.id.lemon2))
        setDraggable(findViewById(R.id.orange2))
        setDraggable(findViewById(R.id.cherry3))
        setDraggable(findViewById(R.id.lemon3))
        setDraggable(findViewById(R.id.orange3))
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
        // Reset the position of all images
        val initialX = 150f
        val initialY = 200f

        val initialX1 = 300f
        val initialY1 = 500f

        val initialX2 = 600f
        val initialY2 = 200f

        val initialX3 = 150f
        val initialY3 = 750f

        findViewById<ImageView>(R.id.cherry).animate().x(initialX).y(initialY).setDuration(0).start()
        findViewById<ImageView>(R.id.lemon).animate().x(initialX).y(initialY).setDuration(0).start()
        findViewById<ImageView>(R.id.orange).animate().x(initialX).y(initialY).setDuration(0).start()

        findViewById<ImageView>(R.id.cherry1).animate().x(initialX1).y(initialY1).setDuration(0).start()
        findViewById<ImageView>(R.id.lemon1).animate().x(initialX1).y(initialY1).setDuration(0).start()
        findViewById<ImageView>(R.id.orange1).animate().x(initialX1).y(initialY1).setDuration(0).start()

        findViewById<ImageView>(R.id.cherry2).animate().x(initialX2).y(initialY2).setDuration(0).start()
        findViewById<ImageView>(R.id.lemon2).animate().x(initialX2).y(initialY2).setDuration(0).start()
        findViewById<ImageView>(R.id.orange2).animate().x(initialX2).y(initialY2).setDuration(0).start()


        findViewById<ImageView>(R.id.cherry3).animate().x(initialX3).y(initialY3).setDuration(0).start()
        findViewById<ImageView>(R.id.lemon3).animate().x(initialX3).y(initialY3).setDuration(0).start()
        findViewById<ImageView>(R.id.orange3).animate().x(initialX3).y(initialY3).setDuration(0).start()

        imagesMovedCount = 0
    }
}


