package com.alancamargo.desafiogithub.users.ui.adapter.repository

import androidx.recyclerview.widget.RecyclerView
import com.alancamargo.desafiogithub.domain.repository.model.Repository
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.users.databinding.ItemRepositoryBinding

internal class RepositoryViewHolder(
    private val binding: ItemRepositoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(repository: Repository) {
        with(binding) {
            txtName.text = repository.name
            txtDescription.text = repository.description
            txtOwner.text = repository.ownerUserName
            val language = txtLanguage.context.getString(
                R.string.language_format,
                repository.language
            )
            txtLanguage.text = language
        }
    }
}
