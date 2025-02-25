package com.nibm.myapplication.repository

import androidx.lifecycle.LiveData
import com.nibm.myapplication.dao.UserDao
import com.nibm.myapplication.model.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    fun getUser(username: String, password: String): LiveData<User?> {
        return userDao.getUser(username, password)
    }
}