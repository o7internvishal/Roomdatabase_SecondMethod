package com.example.O7Solution.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.O7Solution.UserDao
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [User::class], version = 1, exportSchema = false)
 abstract class UserDatabase : RoomDatabase() {
     abstract fun UserDao(): UserDao


    @OptIn(InternalCoroutinesApi::class)
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "userdatabase"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}