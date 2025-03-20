package com.nibm.myapplication.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nibm.myapplication.model.TravelDestination

@Dao
interface TravelDestinationDao {
    @Insert
    suspend fun insert(destination: TravelDestination)

    @Query("SELECT * FROM travel_destinations")
    fun getAllDestinations(): LiveData<List<TravelDestination>>
}