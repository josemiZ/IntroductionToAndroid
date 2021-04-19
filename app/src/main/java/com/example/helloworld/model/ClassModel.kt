package com.example.helloworld.model

import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.eighth_class.EighthClassActivity
import com.example.helloworld.eleventh_class.EleventhClassActivity
import com.example.helloworld.fifth_class.FifthClassActivity
import com.example.helloworld.fourth_class.FourthClassActivity
import com.example.helloworld.ninth_class.NinthClassActivity
import com.example.helloworld.seventh_class.SeventhClassActivity
import com.example.helloworld.sixth_class.SixthClassActivity
import com.example.helloworld.tenth_class.TenthClassActivity
import com.example.helloworld.third_class.ThirdClassActivity

class ClassModel(var id: Int, var name: String, var activity: Class<out AppCompatActivity>) {

    companion object {
        val list: List<ClassModel> = arrayListOf(
            ClassModel(3, "Third Class", ThirdClassActivity::class.java),
            ClassModel(4, "Fourth Class", FourthClassActivity::class.java),
            ClassModel(5, "Fifth Class", FifthClassActivity::class.java),
            ClassModel(6, "Sixth Class", SixthClassActivity::class.java),
            ClassModel(7, "Seventh Class", SeventhClassActivity::class.java),
            ClassModel(8, "Eighth Class", EighthClassActivity::class.java),
            ClassModel(9, "Ninth Class", NinthClassActivity::class.java),
            ClassModel(10, "Tenth Class", TenthClassActivity::class.java),
            ClassModel(11, "Eleventh Class", EleventhClassActivity::class.java)
        )
    }

}