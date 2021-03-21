package com.example.helloworld

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FourthClassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth_class)

        findViewById<Button>(R.id.btn_form).setOnClickListener {
            val emailText = findViewById<EditText>(R.id.et_email).text.toString()
            if (emailText.isBlank()) {
                showToast("El campo email no puede ser nulo")
            } else {
                showToast("Email validado correctamente")
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}