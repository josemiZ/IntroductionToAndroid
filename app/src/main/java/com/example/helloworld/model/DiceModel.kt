package com.example.helloworld.model

class DiceModel(private val sides: Int) {
    fun roll() = (1..sides).random()
}