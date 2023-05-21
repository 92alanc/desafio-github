package com.alancamargo.desafiogithub.users.ui.adapter.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.users.databinding.ItemRepositoryBinding

internal class RepositoryAdapter : ListAdapter<Repository, RepositoryViewHolder>(
    RepositoryDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = getItem(position)
        holder.bindTo(repository)
    }
}
