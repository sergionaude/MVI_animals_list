package com.sergionaude.mvi_animals_list.view

sealed class MainIntent {

    data object FetchAnimals: MainIntent()

}