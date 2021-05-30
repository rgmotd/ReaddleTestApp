package com.example.readdletestapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readdletestapp.Item
import com.example.readdletestapp.data.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Item>>()
    val items: LiveData<List<Item>>
        get() = _items

    init {
        this.generateData()
    }

    fun generateData() {
        viewModelScope.launch {
            _items.value = repository.generateData(100)
        }
    }

    fun simulateChanges() {
        viewModelScope.launch {
            _items.value = repository.simulateDataChanges(_items.value!!)
        }
    }
}