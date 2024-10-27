package com.sergionaude.mvi_animals_list.api

class AnimalRepository(private val animalApi: AnimalApi) {

    suspend fun getAnimals() = animalApi.getAnimals()
}