package com.sergionaude.mvi_animals_list.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergionaude.mvi_animals_list.api.AnimalRepository
import com.sergionaude.mvi_animals_list.view.MainIntent
import com.sergionaude.mvi_animals_list.view.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val animalRepository: AnimalRepository) : ViewModel(){

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _userStates = MutableStateFlow<MainState>(MainState.Idle)
    val userState : StateFlow<MainState>
        get() = _userStates


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{ collector ->
                when(collector){
                    is MainIntent.FetchAnimals -> fetchAnimals()
                }
            }
        }
    }

    private fun fetchAnimals(){
        viewModelScope.launch {
            _userStates.value = MainState.Loading
            _userStates.value = try {
                MainState.Animals(animalRepository.getAnimals())
            }
            catch (e: Exception){
                MainState.Error("${e.message}")
            }
        }
    }
}