package com.otaman.shoestorage.data.shoelist.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.otaman.shoestorage.data.shoelist.ShoeTypesList

@Database(entities = [ShoeTypesList::class], version = 2)
abstract class ShoeTypesListDatabase: RoomDatabase() {
    abstract fun getAllShoeTypeListsDao(): ShoeTypesListDao
}