package com.alancamargo.desafiogithub.users.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class UiUserSummary(
    val userName: String,
    val profilePictureUrl: String
) : Parcelable
