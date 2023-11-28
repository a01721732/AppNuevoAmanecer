package com.nuevo.nuevoamanecer

import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.graphics.Bitmap
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
            .into(object : CustomTarget<Bitmap>() { //Bajarla a un bitmap
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val pieces = sliceImage(resource) // Arreglo de piezas cortadas
                    runOnUiThread {
                        gridLayoutPuzzlePieces.removeAllViews() //Limpiar el gridlayout
                        val screenWidth = resources.displayMetrics.widthPixels
                        val pieceWidth = screenWidth / gridLayoutPuzzlePieces.columnCount
                        val pieceHeight = pieceWidth

                        pieces.forEach { piece -> //Por cada pieza ponerla en un imageview
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

    private var puzzlePieceTags = mutableListOf<Int>()

    // Cortar la imagen en 9 piezas y regresar un arreglo de piezas
    private fun sliceImage(image: Bitmap): List<Bitmap> {
        val pieces = ArrayList<Bitmap>()
        val rows = 3
        val cols = 3

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val piece = Bitmap.createBitmap(image, j * (image.width / cols), i * (image.height / rows), image.width / cols, image.height / rows)
                pieces.add(piece)
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
    private fun displayPuzzlePieces(pieces: List<Bitmap>) {
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
                scaleType = ImageView.ScaleType.FIT_XY
                setImageBitmap(piece)
                setOnTouchListener(touchListener)
            }
            gridLayoutPuzzlePieces.addView(imageView)
            imageView.setOnDragListener(dragListener)
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

                gridLayoutPuzzlePieces.addView(draggedView)
                draggedView.visibility = View.VISIBLE

                draggedView.setOnTouchListener(touchListener)
                piecesPlaced++
                if(piecesPlaced == 9){
                    showPuzzleCompletionDialog()
                }
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.alpha = 1.0f
                true
            }
            else -> false
        }
    }


}
