package com.sergionaude.mvi_animals_list.view.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sergionaude.mvi_animals_list.R
import com.sergionaude.mvi_animals_list.api.AnimalService
import com.sergionaude.mvi_animals_list.model.Animal

class AnimalViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


    private val animalImage = itemView.findViewById<ImageView>(R.id.animal_imageView)
    private val animalName = itemView.findViewById<TextView>(R.id.animal_textView_name)
    private val animalLocation = itemView.findViewById<TextView>(R.id.animal_textView_location)

    fun bind(animal: Animal){
        animalName.text = animal.name
        animalLocation.text =  animal.location
        val url = AnimalService.BASE_URL + animal
        Glide.with(itemView.context)
            .load(url)
            .into(animalImage)
    }
}