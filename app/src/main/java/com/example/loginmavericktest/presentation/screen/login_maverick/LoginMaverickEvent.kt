package com.example.loginmavericktest.presentation.screen.login_maverick

sealed class LoginMaverickEvent {
    object Login: LoginMaverickEvent()
    data class InputUsername(val username: String): LoginMaverickEvent()
    data class InputPassword(val password: String): LoginMaverickEvent()
    object ChangePasswordVisibility: LoginMaverickEvent()
    object CloseDialog: LoginMaverickEvent()
}
