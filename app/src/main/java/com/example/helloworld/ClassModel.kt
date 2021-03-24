package com.example.helloworld

class ClassModel(var id: Int, var name: String) {

    companion object {
        val list: List<ClassModel> = arrayListOf(
            ClassModel(3, "Third Class"),
            ClassModel(4, "Fourth Class")
        )
    }

}