package com.nibm.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nibm.myapplication.repository.TravelDestinationRepository

class TravelDestinationViewModelFactory(
    private val repository: TravelDestinationRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TravelDestinationViewModel::class.java)) {
            return TravelDestinationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}