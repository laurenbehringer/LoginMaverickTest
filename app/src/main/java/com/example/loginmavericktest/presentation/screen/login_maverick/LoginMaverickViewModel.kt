package com.loginmavericktest.notificationengine.presentation.screens.login_redbank

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmavericktest.domain.repository.MaverickRepository
import com.example.loginmavericktest.domain.request.LoginMaverickRequest
import com.example.loginmavericktest.domain.usecase.LoginMaverickUseCase
import com.example.loginmavericktest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginMaverickViewModel @Inject constructor(
    private val repository: MaverickRepository,
    private val loginMaverickUseCase: LoginMaverickUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(LoginMaverickState())
    val state = _state.asStateFlow()

    fun onEvent(event: LoginMaverickEvent) {
        when(event){
            is LoginMaverickEvent.Login -> {
                _state.update {
                    it.copy(isError = false, message = "")
                }
//                login()
                loginMaverick()
            }
            is LoginMaverickEvent.InputUsername -> {
                _state.update {
                    it.copy(username = event.username)
                }
            }
            is LoginMaverickEvent.InputPassword -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }
            is LoginMaverickEvent.ChangePasswordVisibility -> {
                _state.update {
                    it.copy(isShowPassword = !it.isShowPassword)
                }
            }
            is LoginMaverickEvent.CloseDialog -> {
                _state.update {
                    it.copy(isShowDialogResponse = false, dialogContent = "")
                }
            }
            else -> {}
        }
    }

    private fun loginMaverick() {
        val request = LoginMaverickRequest(
            username = "mod1",
            password = "12345678",
            token = "dIWJpTKUSWW2AcMlLrQBTy:APA91bEG1V7d8cswq0j-Q6oapj9GWUoMnO-EgwDfcuNFXdvtEsv1SHL4XViiusVZYC6kXZRLNpcB3g3hNqBZbTnBCGgwlQJAXuW7nISfOKmbWPWhkv7vO9HkVdp5Vpu4uX83bBTJOt15"
        )
        viewModelScope.launch {
            loginMaverickUseCase.invoke(request).collect { data ->
                when (data){
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = it.isLoading)
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            var message = data.message.toString()
                            it.copy(isError = true, message = message, dialogContent = message, isShowDialogResponse = true)
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(dialogContent = data.data.toString(), isShowDialogResponse = true)
                        }
                    }
                }
            }
        }
    }


}