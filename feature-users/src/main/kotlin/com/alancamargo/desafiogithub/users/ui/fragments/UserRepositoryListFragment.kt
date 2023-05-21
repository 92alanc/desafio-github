package com.alancamargo.desafiogithub.users.ui.fragments

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.alancamargo.desafiogithub.core.extensions.args
import com.alancamargo.desafiogithub.core.extensions.observeViewModelFlow
import com.alancamargo.desafiogithub.core.extensions.putArguments
import com.alancamargo.desafiogithub.users.databinding.FragmentUserRepositoriesBinding
import com.alancamargo.desafiogithub.users.ui.adapter.repository.RepositoryAdapter
import com.alancamargo.desafiogithub.users.ui.viewmodel.userrepositorylist.UserRepositoryListViewModel
import com.alancamargo.desafiogithub.users.ui.viewmodel.userrepositorylist.UserRepositoryListViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

@AndroidEntryPoint
internal class UserRepositoryListFragment : Fragment() {

    private var _binding: FragmentUserRepositoriesBinding? = null

    private val binding: FragmentUserRepositoriesBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<UserRepositoryListViewModel>()

    private val adapter = RepositoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        viewModel.getUserRepositories(args.ownerUserName)
    }

    private fun setUpUi() {
        with(binding) {
            recyclerView.adapter = adapter
            error.btTryAgain.setOnClickListener {
                viewModel.getUserRepositories(args.ownerUserName)
            }
        }
    }

    private fun onStateChanged(state: UserRepositoryListViewState) {
        with(state) {
            binding.shimmerContainer.isVisible = isLoading
            binding.error.root.isVisible = error != null
            binding.recyclerView.isVisible = repositories != null

            repositories?.let(adapter::submitList)

            error?.let {
                binding.error.icon.setImageResource(it.icon)
                binding.error.txtMessage.setText(it.message)
            }
        }
    }

    @Parcelize
    data class Args(val ownerUserName: String) : Parcelable

    companion object {
        fun newInstance(args: Args) = UserRepositoryListFragment().putArguments(args)
    }
}
