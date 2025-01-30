package com.openclassrooms.magicgithub

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS
import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS_RANDOM
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import com.openclassrooms.magicgithub.ui.user_list.UserListAdapter
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.*
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4::class)
class UserRepositoryTest {
    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        userRepository = Injection.getRepository()
    }

    @Test
    fun getUsersWithSuccess() {
        val usersActual = userRepository.getUsers()
        val usersExpected: List<User> = FAKE_USERS
        assertEquals(
            usersActual,
            usersExpected
        )
    }

    @Test
    fun generateRandomUserWithSuccess() {
        val initialSize = userRepository.getUsers().size
        userRepository.addRandomUser()
        val user = userRepository.getUsers().last()
        assertEquals(userRepository.getUsers().size, initialSize + 1)
        assertTrue(
            FAKE_USERS_RANDOM.filter {
                it.equals(user)
            }.isNotEmpty()
        )
    }

    @Test
    fun deleteUserWithSuccess() {
        val userToDelete = userRepository.getUsers()[0]
        userRepository.deleteUser(userToDelete)
        Assert.assertFalse(userRepository.getUsers().contains(userToDelete))
    }

    @Test
    fun testUserActivationState() {
        // Récupère un utilisateur de la liste avant modification
        val user = userRepository.getUsers().first()

        // Récupère l'état initial de l'utilisateur
        val initialState = user.isActive

        // Inverse l'état de l'utilisateur et met à jour via le repository
        val updatedUser = user.copy(isActive = !initialState) // Inverse l'état
        userRepository.updateUser(updatedUser)

        // Récupère l'utilisateur mis à jour après modification
        val modifiedUser = userRepository.getUsers().first { it.id == user.id }

        // Vérifie que l'état a changé après la mise à jour
        assertNotEquals("User state should change after deactivation/reactivation", initialState, modifiedUser.isActive)

        // Inverse à nouveau l'état de l'utilisateur pour la réactivation ou désactivation
        val finalUser = modifiedUser.copy(isActive = !modifiedUser.isActive) // Inverse encore une fois
        userRepository.updateUser(finalUser)

        // Récupère l'utilisateur mis à jour après la réactivation
        val finalUpdatedUser = userRepository.getUsers().first { it.id == user.id }

        // Vérifie que l'état a changé à nouveau
        assertNotEquals("User state should change after reactivation/deactivation", modifiedUser.isActive, finalUpdatedUser.isActive)
    }

    @Test
    fun testItemMoving() {
        val initialUsers = userRepository.getUsers().toMutableList()

        // Vérifie que la liste n'est pas vide
        assertNotNull(initialUsers)
        assertTrue("User list should not be empty", initialUsers.isNotEmpty())

        // Récupère les utilisateurs avant de les déplacer
        val fromUser = initialUsers[0]
        val toUser = initialUsers[2]

        // Vérifie les IDs des utilisateurs avant l'échange
        val fromUserId = fromUser.id
        val toUserId = toUser.id

        // Appelle la méthode swapUser pour échanger les utilisateurs
        userRepository.swapUsers(fromUser, toUser)

        // Récupère la liste des utilisateurs après le déplacement
        val updatedUsers = userRepository.getUsers()

        // Vérifie que la taille de la liste n'a pas changé
        assertEquals("The list size should remain the same", initialUsers.size, updatedUsers.size)

        // Vérifie que les utilisateurs ont bien été déplacés
        val swappedFromUser = updatedUsers[0]
        val swappedToUser = updatedUsers[2]

        assertEquals("The user at the first position should have the ID of the original second user", toUserId, swappedFromUser.id)
        assertEquals("The user at the second position should have the ID of the original first user", fromUserId, swappedToUser.id)
    }

}