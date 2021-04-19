package com.example.helloworld.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.model.ClassModel

class MainAdapter(
    private var list: List<ClassModel>,
    private var onClick: (ClassModel) -> Unit
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false).let {
            return ViewHolder(it, onClick)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder(itemView: View, private val onClick: (ClassModel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(model: ClassModel) {
            val text = itemView.findViewById<TextView>(R.id.tv_title)
            text.text = model.name
            text.setOnClickListener { onClick(model) }
        }
    }
}