package com.sergionaude.mvi_animals_list.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimalService {

    private const val BASE_URL = "https://raw.githubusercontent.com/CatalinStefan/animalApi/master/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api : AnimalApi = getRetrofit().create(AnimalApi::class.java)
}