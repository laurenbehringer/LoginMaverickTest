package com.loginmavericktest.notificationengine.presentation.screens.login_redbank

sealed class LoginMaverickEvent {
    object Login: LoginMaverickEvent()
    data class InputUsername(val username: String): LoginMaverickEvent()
    data class InputPassword(val password: String): LoginMaverickEvent()
    object ChangePasswordVisibility: LoginMaverickEvent()
    object CloseDialog: LoginMaverickEvent()
}
