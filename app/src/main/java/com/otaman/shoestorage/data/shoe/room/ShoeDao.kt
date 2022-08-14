package com.otaman.shoestorage.data.shoe.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.otaman.shoestorage.data.shoe.Shoe

@Dao
interface ShoeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoe(shoe: Shoe)

    @Delete
    fun deleteShoe(shoe: Shoe)

    @Query("SELECT * FROM Shoe ORDER BY model_name ASC")
    fun getAllShoes(): LiveData<List<Shoe>>

    @Update
    fun updateShoe(shoe: Shoe)
}