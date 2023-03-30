package com.loginmavericktest.notificationengine.presentation.screens.login_redbank

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmavericktest.R
import com.loginmavericktest.notificationengine.presentation.shared.LoadingDialog
import com.loginmavericktest.notificationengine.presentation.shared.ResponseDialog
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LoginMaverickScreen(
    viewModel: LoginMaverickViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val focus = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val interactionSource = remember {
        MutableInteractionSource()
    }

    fun showSnackBar(message: String?) {
        viewModel.viewModelScope.launch {
            message?.let {
                scaffoldState.snackbarHostState.showSnackbar(it)
            }
        }
    }

    LaunchedEffect(key1 = state.isError, key2 = state.model){
        if (state.isError){
            showSnackBar(state.message)
        }
        if (state.model != null){
//            showSnackBar(state.model!!.OKContent?.fullName)
            showSnackBar(state.model!!.username)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focus.clearFocus()
            }
            .padding(horizontal = 20.dp)
    ) {
        Column {
            if (state.isLoading) {
                println("LOADING ${state.isLoading}")
                LoadingDialog()
            }
            if (state.isShowDialogResponse){
                ResponseDialog(data = state.dialogContent){
                    viewModel.onEvent(LoginMaverickEvent.CloseDialog)
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Text(
                text = "Login Redbank",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = state.username,
                onValueChange = {
                    viewModel.onEvent(LoginMaverickEvent.InputUsername(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                label = {
                    Text(text = "Username")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginMaverickEvent.InputPassword(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        focus.clearFocus()
                        viewModel.onEvent(LoginMaverickEvent.Login)
                    }
                ),
                visualTransformation = if (!state.isShowPassword) PasswordVisualTransformation()
                else VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (state.isShowPassword) R.drawable.ic_not_visible else R.drawable.ic_visible
                        ),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(LoginMaverickEvent.ChangePasswordVisibility)
                        }
                    )
                },
                label = {
                    Text(text = "Password")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Dont have an account? register here!",
                color = Color.Blue,
                fontSize = 15.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
//                        navigator.navigate(RegisterScreenDestination, onlyIfResumed = true)
                    }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
                onClick = {
                    focus.clearFocus()
                    viewModel.onEvent(LoginMaverickEvent.Login)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Login")
            }
        }
    }
}


