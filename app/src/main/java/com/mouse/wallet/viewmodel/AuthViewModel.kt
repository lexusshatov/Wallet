package com.mouse.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.mouse.core.interaction.LoginInteraction

class AuthViewModel(
    val login: LoginInteraction,
) : ViewModel()