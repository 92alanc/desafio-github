package com.alancamargo.desafiogithub.users.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.alancamargo.desafiogithub.core.design.tools.DialogueHelper
import com.alancamargo.desafiogithub.core.extensions.observeViewModelFlow
import com.alancamargo.desafiogithub.navigation.UserDetailsActivityNavigation
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.users.databinding.ActivityUserListBinding
import com.alancamargo.desafiogithub.users.ui.adapter.usersummary.UserSummaryAdapter
import com.alancamargo.desafiogithub.users.ui.tools.SearchQueryListener
import com.alancamargo.desafiogithub.users.ui.viewmodel.userlist.UserListViewAction
import com.alancamargo.desafiogithub.users.ui.viewmodel.userlist.UserListViewModel
import com.alancamargo.desafiogithub.users.ui.viewmodel.userlist.UserListViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class UserListActivity : AppCompatActivity() {

    private var _binding: ActivityUserListBinding? = null

    private val binding: ActivityUserListBinding
        get() = _binding!!

    private val viewModel by viewModels<UserListViewModel>()

    private val adapter by lazy { UserSummaryAdapter(viewModel::onClickUser) }

    @Inject
    lateinit var userDetailsActivityNavigation: UserDetailsActivityNavigation

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        observeViewModelFlow(viewModel.action, ::onAction)
        viewModel.getUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_user_list, menu)
        searchView = (menu.findItem(R.id.item_search)?.actionView as SearchView).apply {
            queryHint = getString(R.string.search)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemAppInfo -> {
                viewModel.onClickShowAppInfo()
                true
            }

            else -> false
        }
    }

    private fun setUpUi() {
        with(binding) {
            setSupportActionBar(toolbar)
            recyclerView.adapter = adapter
            error.btTryAgain.setOnClickListener {
                viewModel.getUsers()
            }
        }
    }

    private fun onStateChanged(state: UserListViewState) {
        with(state) {
            binding.shimmerContainer.isVisible = isLoading
            binding.error.root.isVisible = error != null
            binding.recyclerView.isVisible = users != null

            users?.let {
                adapter.submitList(it)
                val listener = SearchQueryListener(viewModel::searchUser)
                searchView?.setOnQueryTextListener(listener)
            }

            error?.let {
                binding.error.icon.setImageResource(it.icon)
                binding.error.txtMessage.setText(it.message)
            }
        }
    }

    private fun onAction(action: UserListViewAction) {
        when (action) {
            is UserListViewAction.OpenUserDetails -> openUserDetails(action.userName)
            is UserListViewAction.ShowAppInfo -> showAppInfo()
        }
    }

    private fun openUserDetails(userName: String) {
        userDetailsActivityNavigation.startActivity(
            context = this,
            userName = userName
        )
    }

    private fun showAppInfo() {
        dialogueHelper.showDialogue(
            context = this,
            titleRes = R.string.app_info_label,
            messageRes = R.string.app_info
        )
    }
}
