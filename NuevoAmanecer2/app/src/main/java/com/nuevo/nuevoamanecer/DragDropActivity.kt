package com.nuevo.nuevoamanecer

import android.content.ClipData
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.*

//Ya no se usa
class DragDropActivity : AppCompatActivity() {
    private lateinit var gridLayoutPuzzle: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dragdrop)

        gridLayoutPuzzle = findViewById(R.id.gridLayoutPuzzle)


        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE) //Obtener el nombre del usuario de sharedprefs
        val personName = sharedPref.getString("user", "DefaultName")

        val gameType = "dragAndDrop" //Tipo de juego
        val imagePath = "images/$personName/$gameType/imageUrl" // ruta

        fetchAndSliceImage(imagePath)
    }

    private fun fetchAndSliceImage(imagePath: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference(imagePath)

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val imageUrl = dataSnapshot.getValue(String::class.java)
                imageUrl?.let { downloadImage(it) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Mostrar mensaje de error

            }
        })
    }

    private fun downloadImage(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val pieces = sliceImage(resource)
                    runOnUiThread {
                        gridLayoutPuzzle.removeAllViews()
                        val screenWidth = resources.displayMetrics.widthPixels
                        val pieceWidth = screenWidth / gridLayoutPuzzle.columnCount
                        val pieceHeight = pieceWidth

                        pieces.forEach { piece ->
                            val imageView = ImageView(this@DragDropActivity).apply {
                                layoutParams = GridLayout.LayoutParams().apply {
                                    width = pieceWidth
                                    height = pieceHeight
                                }
                                scaleType = ImageView.ScaleType.FIT_XY //
                                setImageBitmap(piece)
                                setOnTouchListener(touchListener)
                            }
                            gridLayoutPuzzle.addView(imageView)
                            imageView.setOnDragListener(dragListener)
                        }
                    }
                }


                override fun onLoadCleared(placeholder: Drawable?) {
                    // Vacio
                }
            })
    }

    private fun sliceImage(image: Bitmap): List<Bitmap> {
        val pieces = ArrayList<Bitmap>()
        val rows = 2
        val cols = 2
        val pieceWidth = image.width / cols
        val pieceHeight = image.height / rows

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                pieces.add(Bitmap.createBitmap(image, j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight))
            }
        }
        return pieces
    }

    private val touchListener = View.OnTouchListener { view, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDrag(data, shadowBuilder, view, 0)
            view.visibility = View.INVISIBLE
            true
        } else {
            false
        }
    }

    private val dragListener = View.OnDragListener { view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> true
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.scaleX = 1.1f
                view.scaleY = 1.1f
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.scaleX = 1.0f
                view.scaleY = 1.0f
                true
            }
            DragEvent.ACTION_DROP -> {
                val v = dragEvent.localState as View
                val owner = v.parent as ViewGroup
                owner.removeView(v)
                val destination = view as ImageView
                val container = destination.parent as ViewGroup
                if (container != owner) {
                    container.removeView(destination)
                    owner.addView(destination)
                    container.addView(v)
                }
                v.visibility = View.VISIBLE
                destination.visibility = View.VISIBLE
                view.scaleX = 1.0f
                view.scaleY = 1.0f
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.scaleX = 1.0f
                view.scaleY = 1.0f
                if (!dragEvent.result) {
                    val v = dragEvent.localState as View
                    v.visibility = View.VISIBLE
                }
                true
            }
            else -> false
        }
    }
}
