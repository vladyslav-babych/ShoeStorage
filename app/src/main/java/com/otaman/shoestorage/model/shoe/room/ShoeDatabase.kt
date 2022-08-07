package com.otaman.shoestorage.model.shoe.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otaman.shoestorage.model.shoe.Shoe

@Database(entities = [Shoe::class], version = 1)
abstract class ShoeDatabase: RoomDatabase() {
    abstract fun getShoeDao(): ShoeDao

    companion object {
        @Volatile
        private var INSTANCE: ShoeDatabase? = null
        fun getInstance(context: Context): ShoeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeDatabase::class.java,
                    "shoe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}