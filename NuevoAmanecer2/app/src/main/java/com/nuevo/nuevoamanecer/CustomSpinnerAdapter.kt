package com.nuevo.nuevoamanecer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomSpinnerAdapter(context: Context, private val items: List<String>) : ArrayAdapter<String>(context, R.layout.spinner_custom_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate your custom layout for selected item
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.selected_item_layout, parent, false)
        val textView = view.findViewById<TextView>(R.id.text)
        val checkmark = view.findViewById<ImageView>(R.id.checkmark)

        textView.text = items[position]
        checkmark.visibility = View.VISIBLE  // Make checkmark visible

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inflate your custom layout for dropdown items
        return super.getDropDownView(position, convertView, parent)
    }
}
