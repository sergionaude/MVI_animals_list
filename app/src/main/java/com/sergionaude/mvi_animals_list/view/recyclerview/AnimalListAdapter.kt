package com.sergionaude.mvi_animals_list.view.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sergionaude.mvi_animals_list.R
import com.sergionaude.mvi_animals_list.model.Animal

class AnimalListAdapter(private val animalList : MutableList<Animal> = mutableListOf<Animal>()) : RecyclerView.Adapter<AnimalViewHolder>()
{
    private fun newAnimals(newAnimalList : List<Animal>){
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AnimalViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.animal_item, parent, false)
        )

    override fun getItemCount() = animalList.size

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.bind(animalList[position])
    }
}