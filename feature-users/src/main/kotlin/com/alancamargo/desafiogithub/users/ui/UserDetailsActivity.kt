package com.alancamargo.desafiogithub.users.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import coil.load
import com.alancamargo.desafiogithub.core.extensions.args
import com.alancamargo.desafiogithub.core.extensions.createIntent
import com.alancamargo.desafiogithub.core.extensions.observeViewModelFlow
import com.alancamargo.desafiogithub.core.extensions.putArguments
import com.alancamargo.desafiogithub.users.R
import com.alancamargo.desafiogithub.users.databinding.ActivityUserDetailsBinding
import com.alancamargo.desafiogithub.users.ui.fragments.UserRepositoryListFragment
import com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails.UserDetailsViewAction
import com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails.UserDetailsViewModel
import com.alancamargo.desafiogithub.users.ui.viewmodel.userdetails.UserDetailsViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize

private const val FRAGMENT_TAG = "repositories"

@AndroidEntryPoint
internal class UserDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityUserDetailsBinding? = null

    private val binding: ActivityUserDetailsBinding
        get() = _binding!!

    private val args by args<Args>()
    private val viewModel by viewModels<UserDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeViewModelFlow(viewModel.state, ::onStateChanged)
        observeViewModelFlow(viewModel.action, ::onAction)
        viewModel.getUserDetails(args.userName)
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.onClickBack()
        return true
    }

    private fun setUpUi() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            error.btTryAgain.setOnClickListener {
                viewModel.getUserDetails(args.userName)
            }
        }
    }

    private fun onStateChanged(state: UserDetailsViewState) {
        with(state) {
            binding.shimmerContainer.isVisible = isLoading
            binding.error.root.isVisible = error != null
            binding.content.isVisible = user != null

            handleUserDetails()
            handleError()
        }
    }

    private fun onAction(action: UserDetailsViewAction) {
        when (action) {
            is UserDetailsViewAction.Finish -> finish()
        }
    }

    private fun UserDetailsViewState.handleUserDetails() {
        user?.let {
            binding.imgProfilePicture.load(it.profilePictureUrl)
            binding.txtUserName.text = it.userName
            binding.txtName.text = it.name
            binding.txtBio.text = it.bio
            binding.txtLocation.text = getString(R.string.location_format, it.location)
            binding.txtPublicRepositoryCount.text = getString(
                R.string.public_repositories_format,
                it.publicRepositoryCount
            )
            binding.txtPublicGistCount.text = getString(
                R.string.public_gists_format,
                it.publicGistCount
            )
            binding.txtFollowerCount.text = getString(
                R.string.followers_format,
                it.followerCount
            )
            binding.txtFollowingCount.text = getString(
                R.string.following_format,
                it.followingCount
            )

            val existingFragment = supportFragmentManager.findFragmentByTag(
                FRAGMENT_TAG
            ) as? UserRepositoryListFragment

            if (existingFragment == null) {
                val fragmentArgs = UserRepositoryListFragment.Args(args.userName)
                val fragment = UserRepositoryListFragment.newInstance(fragmentArgs)

                supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, fragment)
                }
            }
        }
    }

    private fun UserDetailsViewState.handleError() {
        error?.let {
            binding.error.icon.setImageResource(it.icon)
            binding.error.txtMessage.setText(it.message)
        }
    }

    @Parcelize
    data class Args(val userName: String) : Parcelable

    companion object {
        fun getIntent(context: Context, args: Args): Intent {
            return context.createIntent(UserDetailsActivity::class).putArguments(args)
        }
    }
}
