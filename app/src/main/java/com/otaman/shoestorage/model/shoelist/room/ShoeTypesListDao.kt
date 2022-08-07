package com.otaman.shoestorage.model.shoelist.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otaman.shoestorage.model.shoelist.ShoeTypesList

@Dao
interface ShoeTypesListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoeTypeList(shoeTypesList: ShoeTypesList)

    @Delete
    fun deleteShoeTypeList(shoeTypesList: ShoeTypesList)

    @Query("SELECT * FROM ShoeTypesList ORDER BY model_type ASC")
    fun getAllShoeTypeLists(): LiveData<List<ShoeTypesList>>
}