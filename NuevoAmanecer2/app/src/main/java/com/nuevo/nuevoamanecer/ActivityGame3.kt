package com.nuevo.nuevoamanecer

import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
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
    private lateinit var resetButton: Button
    private var personNameG: String? = null
    private var piecesPlaced = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game3)

        val btnRegresar = findViewById<Button>(R.id.btnRegresarJuego3)
        gridLayoutPuzzlePieces = findViewById(R.id.gridLayoutPuzzlePieces)
        gridLayoutPuzzleSpaces = findViewById(R.id.gridLayoutPuzzleSpaces)
        resetButton = findViewById(R.id.btnReset)


        // Sacar de preferencias
        val sharedPref = this.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE) //Obtener el nombre del usuario de sharedprefs
        val personName = sharedPref.getString("user", "DefaultName")



        // Sacar la imagen
        fetchAndSliceImage(personName.toString(),"puzzle")

        btnRegresar.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        resetButton.setOnClickListener {
            gridLayoutPuzzlePieces.removeAllViews()
            gridLayoutPuzzleSpaces.removeAllViews()
            setupPuzzleSpaces()
            fetchAndSliceImage(personName.toString(),"puzzle")
        }


    }

    private fun fetchAndSliceImage(personName: String, gameType: String) {
        // Sacar y obtener la imagen
        val imagesPath = "images/$personName/$gameType"
        val databaseReference = FirebaseDatabase.getInstance().getReference(imagesPath)

        databaseReference.orderByChild("timestamp").limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val latestImageSnapshot = dataSnapshot.children.first()
                        val imageUrl = latestImageSnapshot.child("url").getValue(String::class.java)
                        imageUrl?.let { downloadImage(it) }
                    }
                    else{
                        useDefaultImage()
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Mostrar mensaje de error
                }
            })

        setupPuzzleSpaces()
    }


    //Bajar la imagen de firebase
    private fun downloadImage(imageUrl: String) {
        Log.d("ActivityGame3", "Downloading image from $imageUrl")
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .error(R.drawable.perro)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val pieces = sliceImage(resource) // List of PuzzlePieceView
                    runOnUiThread {
                        gridLayoutPuzzlePieces.removeAllViews()
                        val screenWidth = resources.displayMetrics.widthPixels
                        val pieceWidth = screenWidth / gridLayoutPuzzlePieces.columnCount
                        val pieceHeight = pieceWidth

                        pieces.forEach { puzzlePieceView ->
                            puzzlePieceView.layoutParams = GridLayout.LayoutParams().apply {
                                width = 420
                                height = 420
                                rightMargin = 10
                                bottomMargin = 10
                            }
                            gridLayoutPuzzlePieces.addView(puzzlePieceView)
                            puzzlePieceView.setOnTouchListener(touchListener)
                            puzzlePieceView.setOnDragListener(dragListener)
                        }
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Empty
                }
            })
    }


    private var puzzlePieceTags = mutableListOf<Int>()

    // Cortar la imagen en 9 piezas y regresar un arreglo de piezas
    private fun sliceImage(image: Bitmap): List<PuzzlePieceView> {
        val pieces = ArrayList<PuzzlePieceView>()
        val rows = 3
        val cols = 3
        var pieceNumber = 1

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val pieceBitmap = Bitmap.createBitmap(image, j * (image.width / cols), i * (image.height / rows), image.width / cols, image.height / rows)
                val puzzlePieceView = PuzzlePieceView(this, pieceNumber).apply {
                    setImageBitmap(pieceBitmap)
                    tag = pieceNumber // Tag each view with its piece number
                }
                pieces.add(puzzlePieceView)
                pieceNumber++
            }
        }
        return pieces
    }




    // Si no hay imagen, poner una por default
    private fun useDefaultImage() {
        val defaultImage = ContextCompat.getDrawable(this, R.drawable.perro)?.toBitmap()
        defaultImage?.let {
            val pieces = sliceImage(it)
            runOnUiThread {
                displayPuzzlePieces(pieces)
            }
        }
    }

    // Poner las piezas en el gridlayout
    private fun displayPuzzlePieces(pieces: List<PuzzlePieceView>) {
        gridLayoutPuzzlePieces.removeAllViews()
        val screenWidth = resources.displayMetrics.widthPixels
        val pieceWidth = screenWidth / gridLayoutPuzzlePieces.columnCount
        val pieceHeight = pieceWidth

        pieces.forEach { pieceView ->
            pieceView.layoutParams = GridLayout.LayoutParams().apply {
                width = 420
                height = 420
                rightMargin = 10
                bottomMargin = 10
            }
            gridLayoutPuzzlePieces.addView(pieceView)
            pieceView.setOnTouchListener(touchListener)
        }
    }


    // Poner los espacios para las piezas
    private fun setupPuzzleSpaces() {
        runOnUiThread {
            val totalSpacing = gridLayoutPuzzleSpaces.paddingLeft + gridLayoutPuzzleSpaces.paddingRight
            val screenWidth = resources.displayMetrics.widthPixels - totalSpacing
            val pieceWidth = screenWidth / gridLayoutPuzzleSpaces.columnCount
            val pieceHeight = pieceWidth

            val piecesCount = gridLayoutPuzzleSpaces.rowCount * gridLayoutPuzzleSpaces.columnCount
            for (i in 1..piecesCount) {
                val puzzleSlotView = PuzzlePieceView(this, convertNumberToTag(i)).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        width = 420
                        height = 420
                        rightMargin = 10
                        bottomMargin = 10
                    }
                    imageView.background = ContextCompat.getDrawable(this@ActivityGame3, R.drawable.puzzle_background_placeholder)
                    tag = convertNumberToTag(i) // Tag each view with its slot number
                }
                gridLayoutPuzzleSpaces.addView(puzzleSlotView)
            }
        }
    }




    private fun showPuzzleCompletionDialog() {
        AlertDialog.Builder(this)
            .setTitle("Rompecabezas terminado")
            .setMessage("Felicidades! Has terminado el rompecabezas")
            .setPositiveButton("Reset") { dialog, _ ->
                dialog.dismiss()
                piecesPlaced = 0
                resetPuzzle()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun resetPuzzle() {
        gridLayoutPuzzlePieces.removeAllViews()
        gridLayoutPuzzleSpaces.removeAllViews()
        piecesPlaced = 0
        setupPuzzleSpaces()
        fetchAndSliceImage(personNameG.toString(),"puzzle")
    }

    private fun convertNumberToTag(number: Int): Int {
        return number
        /*
        return when (number) {
            7 -> 4
            8 -> 5
            9 -> 6
            13 -> 7
            14 -> 8
            15 -> 9
            else -> number
        }
        */

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

        Log.d("ActivityGame3", "Drag event: $dragEvent")
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
                val draggedView = dragEvent.localState as PuzzlePieceView
                val destination = view as PuzzlePieceView

                if (draggedView.tag == destination.tag) {
                    val owner = draggedView.parent as ViewGroup
                    owner.removeView(draggedView) // Remove from the current parent

                    // Retrieve the bitmap from the ImageView within the dragged PuzzlePieceView
                    val bitmap = (draggedView.imageView.drawable as BitmapDrawable).bitmap
                    destination.setImageBitmap(bitmap) // Set the bitmap on the destination PuzzlePieceView

                    draggedView.visibility = View.INVISIBLE // Hide the dragged view
                    piecesPlaced++
                    if (piecesPlaced == gridLayoutPuzzlePieces.childCount) {
                        showPuzzleCompletionDialog() // Show completion dialog when all pieces are placed
                    }
                } else {
                    // If the piece is not placed correctly, reset its visibility
                    draggedView.visibility = View.VISIBLE
                }
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                view.alpha = 1.0f
                if (!dragEvent.result) {
                    val v = dragEvent.localState as View
                    v.visibility = View.VISIBLE // Reset visibility if the drag did not result in a drop
                }
                true
            }
            else -> false
        }
    }





}
