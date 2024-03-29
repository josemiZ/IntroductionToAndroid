package com.example.helloworld.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.R
import com.example.helloworld.model.ClassModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_list)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = MainAdapter(ClassModel.list, this::validateOpenActivity)
    }

    private fun validateOpenActivity(model: ClassModel) {
        openActivity(model.activity)
    }

    private fun openActivity(clazz: Class<*>) {
        Intent(this, clazz).also {
            startActivity(it)
        }
    }

}