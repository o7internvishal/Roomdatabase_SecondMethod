package com.example.O7Solution

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.O7Solution.data.User
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)
    @Query("SELECT * FROM student_details ORDER BY id ASC")
    fun readAlldata():List<User>
}