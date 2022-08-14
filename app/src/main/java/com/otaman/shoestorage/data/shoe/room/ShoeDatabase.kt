package com.otaman.shoestorage.data.shoe.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otaman.shoestorage.data.shoe.Shoe

@Database(entities = [Shoe::class], version = 1)
abstract class ShoeDatabase: RoomDatabase() {
    abstract fun getShoeDao(): ShoeDao
}