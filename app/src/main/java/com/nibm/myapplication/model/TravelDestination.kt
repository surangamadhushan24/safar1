package com.nibm.myapplication.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travel_destinations")
data class TravelDestination(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val imageResIds: List<Int>
)