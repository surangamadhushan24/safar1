package com.nibm.myapplication.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nibm.myapplication.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): LiveData<User?>

    @Query("SELECT * FROM users") // Add this query to fetch all users
    fun getAllUsers(): LiveData<List<User>>
}