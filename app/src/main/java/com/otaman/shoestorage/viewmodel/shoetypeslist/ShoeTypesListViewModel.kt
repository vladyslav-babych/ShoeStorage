package com.otaman.shoestorage.viewmodel.shoetypeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otaman.shoestorage.data.shoelist.ShoeTypesList
import com.otaman.shoestorage.data.shoelist.ShoeTypesListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoeTypesListViewModel @Inject constructor(
    private val repository: ShoeTypesListRepository
): ViewModel() {
    val allShoeTypesList: LiveData<List<ShoeTypesList>> = repository.getAllShoeTypeLists()

    fun addShoeTypeList(shoeTypesList: ShoeTypesList) = viewModelScope.launch {
        repository.insertShoeTypeList(shoeTypesList)
    }

    fun deleteShoeTypeList(shoeTypesList: ShoeTypesList) = viewModelScope.launch {
        repository.deleteShoeTypeList(shoeTypesList)
    }

    fun updateShoeModelType(shoeTypesList: ShoeTypesList) = viewModelScope.launch {
        repository.updateShoeModelType(shoeTypesList)
    }
}