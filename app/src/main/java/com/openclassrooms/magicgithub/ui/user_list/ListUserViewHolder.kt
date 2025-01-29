package com.openclassrooms.magicgithub.ui.user_list

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.R
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator

import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import android.graphics.Color


class ListUserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {
    // FOR DESIGN ---
    private var avatar: ImageView = itemView.findViewById(R.id.item_list_user_avatar)
    private val username: TextView = itemView.findViewById(R.id.item_list_user_username)
    private val deleteButton: ImageButton = itemView.findViewById(R.id.item_list_user_delete_button)

    fun bind(user: User, callback: UserListAdapter.Listener) {

        // Utilisation de la Fake API pour générer l'URL de l'avatar
        val avatarUrl = FakeApiServiceGenerator.getAvatarUrl(user.login) // Utilise ici le nom de l'utilisateur

        Glide.with(binding.root.context)
            .load(avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.itemListUserAvatar)  // L'image sera affichée ici dans l'ImageView
        username.text = user.login
        deleteButton.setOnClickListener { callback.onClickDelete(user) }

        // Changer la couleur de fond selon l'état actif/inactif
        binding.root.setBackgroundColor(
            if (user.isActive) Color.WHITE else Color.RED
        )

        binding.itemListUserDeleteButton.setOnClickListener { callback.onClickDelete(user) }
    }

}