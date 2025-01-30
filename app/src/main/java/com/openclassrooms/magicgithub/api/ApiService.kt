package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

interface ApiService {
    fun getUsers(): List<User>
    fun addRandomUser()
    fun deleteUser(username: User)
    fun updateUser(user: User)
    fun swapUsers(user1: User, user2: User)
}