package com.example.helloworld.fifth_class

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.R
import com.example.helloworld.model.DiceModel

class FifthClassActivity : AppCompatActivity() {

    private val diceModel = DiceModel(6)
    lateinit var textView: TextView
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth_class)
        setupContent()
        rollDice()
    }

    private fun setupContent() {
        textView = findViewById(R.id.tv_dice)
        imageView = findViewById(R.id.iv_dice)
        findViewById<Button>(R.id.btn_roll).setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val roll = diceModel.roll()
        textView.text = roll.toString()
        val drawableResource = when (roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        imageView.setImageResource(drawableResource)
        imageView.contentDescription = roll.toString()
    }

}