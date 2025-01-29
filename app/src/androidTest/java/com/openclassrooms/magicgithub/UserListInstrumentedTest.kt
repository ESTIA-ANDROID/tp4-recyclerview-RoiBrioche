package com.openclassrooms.magicgithub

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.openclassrooms.magicgithub.di.Injection.getRepository
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.ui.user_list.ListUserActivity
import com.openclassrooms.magicgithub.ui.user_list.UserListAdapter
import com.openclassrooms.magicgithub.utils.RecyclerViewUtils.ItemCount
import com.openclassrooms.magicgithub.utils.RecyclerViewUtils.clickChildView
import com.openclassrooms.magicgithub.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



/**
 * Instrumented test, which will execute on an Android device.
 * Testing ListUserActivity screen.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class UserListInstrumentedTest {
    @Rule
    @JvmField
    val mActivityRule = ActivityTestRule(ListUserActivity::class.java)

    private lateinit var userRepository: UserRepository


    private var currentUsersSize = -1

    @Before
    fun setup() {
        currentUsersSize = getRepository().getUsers().size
    }

    @Test
    fun checkIfRecyclerViewIsNotEmpty() {
        Espresso.onView(ViewMatchers.withId(R.id.activity_list_user_rv))
            .check(ItemCount(currentUsersSize))
    }

    @Test
    fun checkIfAddingRandomUserIsWorking() {
        Espresso.onView(ViewMatchers.withId(R.id.activity_list_user_fab))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.activity_list_user_rv))
            .check(ItemCount(currentUsersSize + 1))
    }

    @Test
    fun checkIfRemovingUserIsWorking() {
        Espresso.onView(ViewMatchers.withId(R.id.activity_list_user_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    clickChildView(R.id.item_list_user_delete_button)
                )
            )
        Espresso.onView(ViewMatchers.withId(R.id.activity_list_user_rv))
            .check(ItemCount(currentUsersSize - 1))
    }

    /*
    @Test
    fun testSwipeToDeactivateUser() {
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = recyclerView.adapter as UserListAdapter
        val initialUser = adapter.getUserAt(0)

        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ListUserViewHolder>(0, swipeLeft()))

        assertFalse(initialUser.isActive)
    }*/
/*
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
*/
}