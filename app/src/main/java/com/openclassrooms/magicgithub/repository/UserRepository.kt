package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User


class UserRepository(
    private val apiService: ApiService
) {
    fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    fun addRandomUser() {
        // Appeler la fonction addRandomUser() de FakeApiService pour ajouter un utilisateur aléatoire
        apiService.addRandomUser()
    }

    fun deleteUser(user: User) {
        apiService.deleteUser(user) // Retirer l'utilisateur de la liste
    }

    fun updateUser(user: User) {
        apiService.updateUser(user)
    }

    fun swapUsers(user1: User, user2: User) {
        apiService.swapUsers(user1, user2)  // Nouvelle méthode à ajouter dans FakeApiService
    }

}