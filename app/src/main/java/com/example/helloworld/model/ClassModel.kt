package com.example.helloworld.model

class ClassModel(var id: Int, var name: String) {

    companion object {
        val list: List<ClassModel> = arrayListOf(
            ClassModel(3, "Third Class"),
            ClassModel(4, "Fourth Class"),
            ClassModel(5, "Fifth Class"),
            ClassModel(6,"Sixth Class"),
            ClassModel(7,"Seventh Class")
        )
    }

}