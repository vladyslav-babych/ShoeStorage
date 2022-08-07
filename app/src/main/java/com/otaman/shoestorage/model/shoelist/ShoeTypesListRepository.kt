package com.otaman.shoestorage.model.shoelist

import androidx.lifecycle.LiveData
import com.otaman.shoestorage.model.shoelist.room.ShoeTypesListDao

class ShoeTypesListRepository private constructor(private val shoeTypesListDao: ShoeTypesListDao) {
    fun getAllShoeTypeLists(): LiveData<List<ShoeTypesList>> = shoeTypesListDao.getAllShoeTypeLists()

    fun insertShoeTypeList(shoeTypesList: ShoeTypesList) {
        shoeTypesListDao.insertShoeTypeList(shoeTypesList)
    }

    fun deleteShoeTypeList(shoeTypesList: ShoeTypesList) {
        shoeTypesListDao.deleteShoeTypeList(shoeTypesList)
    }

    companion object {
        private var instance: ShoeTypesListRepository? = null
        fun getInstance(shoeTypesListDao: ShoeTypesListDao): ShoeTypesListRepository {
            if(instance == null) {
                instance = ShoeTypesListRepository(shoeTypesListDao)
            }
            return instance!!
        }
    }
}