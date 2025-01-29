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

    // Test pour vérifier que l'état 'actif' ou 'inactif' de l'utilisateur est bien mis à jour
    @Test
    fun testUserActivationState() {
        val user = userRepository.getUsers()[0]

        // Vérifie que l'utilisateur est initialement actif
        assertTrue("User should be active initially", user.isActive)

        // Désactive l'utilisateur
        user.isActive = false

        // Vérifie que l'utilisateur est bien inactif
        assertFalse("User should be inactive after deactivation", user.isActive)

        // Réactive l'utilisateur
        user.isActive = true

        // Vérifie que l'utilisateur est bien réactivé
        assertTrue("User should be active after reactivation", user.isActive)
    }

    @Test
    fun testItemMoving() {
        val fromPosition = 0
        val toPosition = 2
        val users = userRepository.getUsers()

        // Crée une instance de l'adapter
        val adapter = UserListAdapter(object : UserListAdapter.Listener {
            override fun onClickDelete(user: User) {
                // Implémentation vide pour le test
            }
        })

        // Vérifie l'ordre initial des utilisateurs
        assertEquals("001", users[fromPosition].id)
        assertEquals("003", users[toPosition].id)

        // Appel à la méthode de déplacement
        adapter.onItemMove(fromPosition, toPosition)

        // Vérifie que les utilisateurs ont bien été déplacés
        assertEquals("003", users[fromPosition].id)
        assertEquals("001", users[toPosition].id)

        // Vérifie que la taille de la liste reste la même
        assertEquals(3, users.size)
    }
}