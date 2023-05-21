package com.alancamargo.desafiogithub.users.ui.tools

import androidx.appcompat.widget.SearchView

internal class SearchQueryListener(
    private val onSubmit: (String) -> Unit
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let(onSubmit)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // Not necessary
        return false
    }
}
