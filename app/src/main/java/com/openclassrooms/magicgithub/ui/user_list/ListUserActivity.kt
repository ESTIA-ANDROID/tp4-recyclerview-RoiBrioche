package com.openclassrooms.magicgithub.ui.user_list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.di.Injection.getRepository
import com.openclassrooms.magicgithub.model.User
import androidx.recyclerview.widget.ItemTouchHelper

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    // FOR DESIGN ---
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    // FOR DATA ---
    private lateinit var adapter: UserListAdapter

    // OVERRIDE ---
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        configureFab()
        configureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    // CONFIGURATION ---
    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.activity_list_user_rv)
        adapter = UserListAdapter(this)
        recyclerView.adapter = adapter



        // Ajout du Swipe
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,// On permet le déplacement vertical
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT// On garde les options de swipe
        ) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition

                // Déplacer l'élément dans la liste
                adapter.onItemMove(fromPosition, toPosition)

                // Notifier l'adaptateur pour rafraîchir l'affichage
                adapter.notifyItemMoved(fromPosition, toPosition)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val user = adapter.getUserAt(position) // Assure-toi d'ajouter cette méthode dans UserListAdapter

                // Basculer entre actif/inactif
                user.isActive = !user.isActive

                // Met à jour l'affichage de la cellule
                adapter.notifyItemChanged(position)
            }
        }

        // Attacher le swipe au RecyclerView
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView)
    }

    private fun configureFab() {
        fab = findViewById(R.id.activity_list_user_fab)
        fab.setOnClickListener(View.OnClickListener { view: View? ->
            getRepository().addRandomUser()
            loadData()
        })
    }

    private fun loadData() {
        adapter.updateList(getRepository().getUsers())
    }

    // ACTIONS ---
    override fun onClickDelete(user: User) {
        Log.d(ListUserActivity::class.java.name, "User tries to delete a item.")
        getRepository().deleteUser(user)
        loadData()
    }

}