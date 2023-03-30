package com.example.loginmavericktest.presentation.screen.login_maverick

import com.example.loginmavericktest.domain.model.LoginMaverickModel

data class LoginMaverickState(
    // Input
    val username: String = "",
    val password: String = "",

    // Status
    val model: LoginMaverickModel? = null,
    val message: String? = "",
    val isShowPassword: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isShowDialogResponse: Boolean = false,
    val dialogContent: String = ""
)
