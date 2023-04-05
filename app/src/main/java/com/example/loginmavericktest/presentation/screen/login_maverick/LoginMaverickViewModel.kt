package com.example.loginmavericktest.presentation.screen.login_maverick

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmavericktest.domain.repository.MaverickRepository
import com.example.loginmavericktest.domain.request.LoginMaverickRequest
import com.example.loginmavericktest.domain.usecase.LoginMaverickUseCase
import com.example.loginmavericktest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginMaverickViewModel @Inject constructor(
    private val repository: MaverickRepository,
    private val loginMaverickUseCase: LoginMaverickUseCase,
    @ApplicationContext private val context: Context,
    ): ViewModel() {
    private val _state = MutableStateFlow(LoginMaverickState())
    val state = _state.asStateFlow()
    val shared: SharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)


    init {
        val shared: SharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE)
        Log.d("wk", shared.getString("token", "") ?: "")
    }

    fun onEvent(event: LoginMaverickEvent) {
        when(event){
            is LoginMaverickEvent.Login -> {
                val lol: String = shared.getString("token", "") ?: ""
                Log.d("TOKEN WLMFAO", lol)
                _state.update {
                    it.copy(isError = false, message = "")
                }
//                login()
                loginMaverick(lol)
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

    private fun loginMaverick(token : String) {
        val request = LoginMaverickRequest(
            username = "mod1",
            password = "12345678",
            token = token,
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