package com.nuevo.nuevoamanecer

import android.content.Context
import android.graphics.Bitmap
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class PuzzlePieceView(context: Context, val pieceNumber: Int) : FrameLayout(context) {
    val imageView: ImageView = ImageView(context).apply {
        scaleType = ImageView.ScaleType.FIT_XY
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    val numberView: TextView = TextView(context).apply {
        text = pieceNumber.toString()
        // Position the TextView in the top-left corner
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            setMargins(8, 8, 0, 0) // Adjust margins as needed
        }
    }

    init {
        addView(imageView)
        addView(numberView)
    }

    fun setImageBitmap(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }
}
