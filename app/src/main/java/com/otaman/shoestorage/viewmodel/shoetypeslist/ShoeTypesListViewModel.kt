package com.otaman.shoestorage.viewmodel.shoetypeslist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.otaman.shoestorage.model.shoelist.ShoeTypesList
import com.otaman.shoestorage.model.shoelist.ShoeTypesListRepository
import com.otaman.shoestorage.model.shoelist.room.ShoeTypesListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoeTypesListViewModel(application: Application): AndroidViewModel(application) {
    private val dao = ShoeTypesListDatabase.getInstance(application).getAllShoeTypeListsDao()
    private val repository = ShoeTypesListRepository.getInstance(dao)
    val allShoeTypesList: LiveData<List<ShoeTypesList>> = repository.getAllShoeTypeLists()

    fun deleteShoeTypeList(shoeTypesList: ShoeTypesList) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteShoeTypeList(shoeTypesList)
    }
}