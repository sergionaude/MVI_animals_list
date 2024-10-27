package com.sergionaude.mvi_animals_list

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergionaude.mvi_animals_list.api.AnimalService
import com.sergionaude.mvi_animals_list.databinding.ActivityMainBinding
import com.sergionaude.mvi_animals_list.model.Animal
import com.sergionaude.mvi_animals_list.view.MainIntent
import com.sergionaude.mvi_animals_list.view.MainState
import com.sergionaude.mvi_animals_list.view.recyclerview.AnimalListAdapter
import com.sergionaude.mvi_animals_list.vm.MainViewModel
import com.sergionaude.mvi_animals_list.vm.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainViewModel
    private var binding : ActivityMainBinding? = null
    private var animalAdapter = AnimalListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, ViewModelFactory(AnimalService.api)).get(MainViewModel::class.java)

        binding?.recyclerView?.apply {
            adapter = animalAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(
                context, LinearLayoutManager.VERTICAL)
            )
        }

        binding?.button?.setOnClickListener {
            lifecycleScope.launch {
                viewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }

        initObserver()

        setContentView(binding!!.root)
    }

    private fun initObserver(){
        lifecycleScope.launch {
            viewModel.userState.collect{ collector ->
                when(collector){
                    is MainState.Loading -> {
                        binding?.apply {
                            recyclerView.visibility = View.GONE
                            button.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is MainState.Animals -> {
                        binding?.apply {
                            recyclerView.visibility = View.VISIBLE
                            button.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                        collector.animals.let {  animalList ->
                            animalAdapter.newAnimals(animalList)
                        }
                    }
                    is MainState.Error -> {
                        binding?.apply {
                            button.visibility = View.GONE
                            progressBar.visibility = View.GONE
                        }
                        Toast.makeText(this@MainActivity, "An error ${collector.error}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }
    }
}