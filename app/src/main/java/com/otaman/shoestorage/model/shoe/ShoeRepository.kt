package com.otaman.shoestorage.model.shoe

import androidx.lifecycle.LiveData
import com.otaman.shoestorage.model.shoe.room.ShoeDao

class ShoeRepository private constructor(private val shoeDao: ShoeDao) {
    fun getAllShoes(): LiveData<List<Shoe>> = shoeDao.getAllShoes()
}