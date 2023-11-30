package com.nuevo.nuevoamanecer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat


// Clase personalizada para mostrar las piezas del puzzle
//Tiene la imagen, y un numero para la pieza
class PuzzlePieceView(context: Context, val pieceNumber: Int) : FrameLayout(context) {
    val imageView: ImageView = ImageView(context).apply {
        scaleType = ImageView.ScaleType.CENTER_CROP
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    }

    val numberView: TextView = TextView(context).apply {
        text = pieceNumber.toString()
        // Posicionar el número en la esquina superior izquierda
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(8, 8, 0, 0)
        }
    }

    init { // Inicializar el PuzzlePieceView
        addView(imageView)
        addView(numberView)
        numberView.apply {
            setTextColor(ContextCompat.getColor(context, android.R.color.black)) // Texto negro
            setTypeface(numberView.typeface, Typeface.BOLD) // Hacer el texto bold
            textSize = 18f

        }
    }

    // Bitmap
    fun setImageBitmap(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }
}
