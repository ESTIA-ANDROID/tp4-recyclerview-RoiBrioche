package com.openclassrooms.magicgithub.ui.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.utils.UserDiffCallback
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding

class UserListAdapter(  // FOR CALLBACK ---
    private val callback: Listener
) : RecyclerView.Adapter<ListUserViewHolder>() {
    // FOR DATA ---
    private var users:  MutableList<User> = ArrayList()

    // Méthode pour déplacer un élément
    fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition != toPosition) {
            val user = users.removeAt(fromPosition)  // Supprimer l'élément de sa position d'origine
            users.add(toPosition, user)  // Ajouter l'élément à la nouvelle position
        }
    }

    interface Listener {
        fun onClickDelete(user: User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListUserViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        holder.bind(users[position], callback)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    // PUBLIC API ---
    fun updateList(newList: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffCallback(newList, users))
        users.clear()  // Réinitialiser la liste
        users.addAll(newList)  // Ajouter tous les éléments de la nouvelle liste
        diffResult.dispatchUpdatesTo(this)
    }

    fun getUserAt(position: Int): User {
        return users[position] // Vérifie que "users" est bien ta liste de données
    }
}