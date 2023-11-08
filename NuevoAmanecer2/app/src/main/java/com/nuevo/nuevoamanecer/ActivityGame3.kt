package com.nuevo.nuevoamanecer

import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ActivityGame3 : AppCompatActivity() {
    private lateinit var gridLayoutPuzzlePieces: GridLayout
    private lateinit var gridLayoutPuzzleSpaces: GridLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game3)

        val btnRegresar = findViewById<Button>(R.id.btnRegresarJuego3)
        gridLayoutPuzzlePieces = findViewById(R.id.gridLayoutPuzzlePieces)
        gridLayoutPuzzleSpaces = findViewById(R.id.gridLayoutPuzzleSpaces)


        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE) //Obtener el nombre del usuario de sharedprefs
        val personName = sharedPref.getString("user", "DefaultName")

        val gameType = "dragAndDrop" //Tipo de juego
        val imagePath = "images/$personName/$gameType/imageUrl" // ruta

        fetchAndSliceImage(imagePath)

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

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

        setupPuzzleSpaces()
    }

    private fun downloadImage(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val pieces = sliceImage(resource)
                    runOnUiThread {
                        gridLayoutPuzzlePieces.removeAllViews()
                        val screenWidth = resources.displayMetrics.widthPixels
                        val pieceWidth = screenWidth / gridLayoutPuzzlePieces.columnCount
                        val pieceHeight = pieceWidth

                        pieces.forEach { piece ->
                            val imageView = ImageView(this@ActivityGame3).apply {
                                layoutParams = GridLayout.LayoutParams().apply {
                                    width = pieceWidth
                                    height = pieceHeight
                                }
                                scaleType = ImageView.ScaleType.FIT_XY //
                                setImageBitmap(piece)
                                setOnTouchListener(touchListener)
                            }
                            gridLayoutPuzzlePieces.addView(imageView)
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
        val rows = 3
        val cols = 3
        val pieceWidth = (image.width / cols)-50
        val pieceHeight = (image.height / rows)-50

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                pieces.add(Bitmap.createBitmap(image, j * pieceWidth, i * pieceHeight, pieceWidth, pieceHeight))
            }
        }
        return pieces
    }

    private fun setupPuzzleSpaces() {
        runOnUiThread {
            val totalSpacing = gridLayoutPuzzleSpaces.paddingLeft + gridLayoutPuzzleSpaces.paddingRight
            val screenWidth = resources.displayMetrics.widthPixels - totalSpacing
            val pieceWidth = screenWidth / gridLayoutPuzzleSpaces.columnCount
            val pieceHeight = pieceWidth

            val piecesCount = gridLayoutPuzzleSpaces.rowCount * gridLayoutPuzzleSpaces.columnCount
            for (i in 0 until piecesCount) {
                val imageView = ImageView(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = pieceWidth
                        height = pieceHeight
                        rightMargin = 10
                        bottomMargin = 10
                    }
                    scaleType = ImageView.ScaleType.FIT_XY
                    background = ContextCompat.getDrawable(this@ActivityGame3, R.drawable.puzzle_background_placeholder)
                    setOnDragListener(dragListener)
                }
                gridLayoutPuzzleSpaces.addView(imageView)
            }
        }
    }



    private val touchListener = View.OnTouchListener { view, motionEvent ->
        if (motionEvent.action == MotionEvent.ACTION_DOWN) {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = View.DragShadowBuilder(view)
            view.startDrag(data, shadowBuilder, view, 0)
            true
        } else {
            false
        }
    }

    private val dragListener = View.OnDragListener { view, dragEvent ->
        when (dragEvent.action) {
            DragEvent.ACTION_DRAG_STARTED -> true
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.alpha = 0.5f
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                view.alpha = 1.0f
                true
            }
            DragEvent.ACTION_DROP -> {
                val draggedView = dragEvent.localState as View
                val owner = draggedView.parent as ViewGroup
                owner.removeView(draggedView)

                val destination = view as ImageView
                destination.setImageBitmap((draggedView as ImageView).drawable.toBitmap())

                draggedView.visibility = View.VISIBLE
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                view.alpha = 1.0f
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
