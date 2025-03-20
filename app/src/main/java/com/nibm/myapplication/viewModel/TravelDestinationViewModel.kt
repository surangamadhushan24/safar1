package com.nibm.myapplication.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nibm.myapplication.model.TravelDestination
import com.nibm.myapplication.repository.TravelDestinationRepository
import kotlinx.coroutines.launch

class TravelDestinationViewModel(private val repository: TravelDestinationRepository) : ViewModel() {

    val allDestinations: LiveData<List<TravelDestination>> = repository.allDestinations

    fun insert(destination: TravelDestination) = viewModelScope.launch {
        repository.insert(destination)
    }
}