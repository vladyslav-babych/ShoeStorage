package com.otaman.shoestorage.data.shoe

import androidx.lifecycle.LiveData
import com.otaman.shoestorage.data.shoe.room.ShoeDao
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ShoeRepository @Inject constructor(
    private val shoeDao: ShoeDao,
    private val ioDispatcher: CoroutineDispatcher
    ) {
    fun getAllShoes(): LiveData<List<Shoe>> = shoeDao.getAllShoes()
}