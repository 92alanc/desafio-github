package com.alancamargo.desafiogithub.users.ui.adapter.usersummary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary
import com.alancamargo.desafiogithub.users.databinding.ItemUserSummaryBinding

internal class UserSummaryAdapter : ListAdapter<UserSummary, UserSummaryViewHolder>(
    UserSummaryDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSummaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSummaryBinding.inflate(inflater, parent, false)
        return UserSummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserSummaryViewHolder, position: Int) {
        val userSummary = getItem(position)
        holder.bindTo(userSummary)
    }
}
