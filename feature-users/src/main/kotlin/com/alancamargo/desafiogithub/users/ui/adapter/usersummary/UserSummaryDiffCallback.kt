package com.alancamargo.desafiogithub.users.ui.adapter.usersummary

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.desafiogithub.domain.usersummary.model.UserSummary

internal object UserSummaryDiffCallback : DiffUtil.ItemCallback<UserSummary>() {

    override fun areItemsTheSame(oldItem: UserSummary, newItem: UserSummary): Boolean {
        return oldItem.userName == newItem.userName
    }

    override fun areContentsTheSame(oldItem: UserSummary, newItem: UserSummary): Boolean {
        return oldItem == newItem
    }
}
