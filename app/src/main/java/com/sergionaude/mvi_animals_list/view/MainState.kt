package com.sergionaude.mvi_animals_list.view

import com.sergionaude.mvi_animals_list.model.Animal

sealed class MainState {

    data object Idle : MainState()

    data object Loading : MainState()

    data class Animals(val animals: List<Animal>) : MainState()

    data class Error(val error: String) : MainState()
}