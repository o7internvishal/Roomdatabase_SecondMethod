package com.example.O7Solution.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_details")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val firstname: String,
    val lastname: String,
    val age:Int
) {

}

