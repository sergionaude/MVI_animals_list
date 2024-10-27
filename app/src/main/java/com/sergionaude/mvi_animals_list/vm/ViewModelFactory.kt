package com.sergionaude.mvi_animals_list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sergionaude.mvi_animals_list.api.AnimalApi
import com.sergionaude.mvi_animals_list.api.AnimalRepository

class ViewModelFactory(private val api: AnimalApi) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(AnimalRepository(api)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}