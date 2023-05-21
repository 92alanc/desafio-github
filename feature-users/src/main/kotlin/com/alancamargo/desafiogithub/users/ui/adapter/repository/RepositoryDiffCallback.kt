package com.alancamargo.desafiogithub.users.ui.adapter.repository

import androidx.recyclerview.widget.DiffUtil
import com.alancamargo.desafiogithub.domain.repository.model.Repository

internal object RepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}
