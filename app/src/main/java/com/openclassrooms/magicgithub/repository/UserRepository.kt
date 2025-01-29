package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User


class UserRepository(
    private val apiService: ApiService
) {
    // Liste locale pour stocker les utilisateurs (peut aussi être remplacée par une base de données ou autre mécanisme)
    private val userList = mutableListOf<User>()

    fun getUsers(): List<User> {

        // Pour simuler l'appel à l'API et obtenir une liste d'utilisateurs
        val usersFromApi = apiService.getUsers() // Appel à l'API pour obtenir les utilisateurs
        userList.clear() // Vider la liste locale (si nécessaire)
        userList.addAll(usersFromApi) // Ajouter les utilisateurs récupérés depuis l'API à la liste locale
        return userList
    }

    fun addRandomUser() {
        // Appeler la fonction addRandomUser() de FakeApiService pour ajouter un utilisateur aléatoire
        apiService.addRandomUser()
    }

    fun deleteUser(user: User) {
        apiService.deleteUser(user) // Retirer l'utilisateur de la liste
    }

    fun switch(user1: User, user2: User) {
        getUsers()
    }

}