package com.nibm.myapplication.repository

import androidx.lifecycle.LiveData
import com.nibm.myapplication.dao.TravelDestinationDao
import com.nibm.myapplication.model.TravelDestination

class TravelDestinationRepository(private val travelDestinationDao: TravelDestinationDao) {

    val allDestinations: LiveData<List<TravelDestination>> = travelDestinationDao.getAllDestinations()

    suspend fun insert(destination: TravelDestination) {
        travelDestinationDao.insert(destination)
    }
}