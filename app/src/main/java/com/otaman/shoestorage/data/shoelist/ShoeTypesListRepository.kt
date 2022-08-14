package com.otaman.shoestorage.data.shoelist

import androidx.lifecycle.LiveData
import com.otaman.shoestorage.data.shoelist.room.ShoeTypesListDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShoeTypesListRepository @Inject constructor(
    private val shoeTypesListDao: ShoeTypesListDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getAllShoeTypeLists(): LiveData<List<ShoeTypesList>> = shoeTypesListDao.getAllShoeTypeLists()

    suspend fun insertShoeTypeList(shoeTypesList: ShoeTypesList) {
        withContext(ioDispatcher) {
            shoeTypesListDao.insertShoeTypeList(shoeTypesList)
        }
    }

    suspend fun deleteShoeTypeList(shoeTypesList: ShoeTypesList) {
        withContext(ioDispatcher) {
            shoeTypesListDao.deleteShoeTypeList(shoeTypesList)
        }
    }

    suspend fun updateShoeModelType(shoeTypesList: ShoeTypesList) {
        withContext(ioDispatcher) {
            shoeTypesListDao.updateShoeModelType(shoeTypesList)
        }
    }
}