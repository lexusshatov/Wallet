package com.mouse.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.repository.FakeAuthentication
import com.mouse.core.interaction.LoginInteraction

class AuthViewModel : ViewModel() {
    val login: LoginInteraction = LoginInteraction(FakeAuthentication())
}