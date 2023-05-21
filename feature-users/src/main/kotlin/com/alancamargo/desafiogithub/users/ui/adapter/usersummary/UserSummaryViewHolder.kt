package com.alancamargo.desafiogithub.users.ui.adapter.usersummary

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.databinding.ItemUserSummaryBinding

internal class UserSummaryViewHolder(
    private val binding: ItemUserSummaryBinding,
    private val onItemClick: (UserSummary) -> Unit
) : ViewHolder(binding.root) {

    fun bindTo(userSummary: UserSummary) {
        with(binding) {
            root.setOnClickListener { onItemClick(userSummary) }
            imgProfilePicture.load(userSummary.profilePictureUrl)
            txtUserName.text = userSummary.userName
        }
    }
}
