package com.sergionaude.mvi_animals_list.api

import com.sergionaude.mvi_animals_list.model.Animal
import retrofit2.http.GET

interface AnimalApi {

    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>
}