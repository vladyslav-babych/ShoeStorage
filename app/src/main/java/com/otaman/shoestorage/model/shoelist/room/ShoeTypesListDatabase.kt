package com.otaman.shoestorage.model.shoelist.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.otaman.shoestorage.model.shoelist.ShoeTypesList

@Database(entities = [ShoeTypesList::class], version = 1)
abstract class ShoeTypesListDatabase: RoomDatabase() {
    abstract fun getAllShoeTypeListsDao(): ShoeTypesListDao

    companion object {
        @Volatile
        private var INSTANCE: ShoeTypesListDatabase? = null
        fun getInstance(context: Context): ShoeTypesListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeTypesListDatabase::class.java,
                    "shoe_types_list_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}