package com.mouse.wallet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.User
import com.mouse.core.api.UserRepository

class UserViewModel(
    userRepository: UserRepository,
) : ViewModel() {
    val user: User = userRepository.user
}