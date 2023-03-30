package com.loginmavericktest.notificationengine.presentation.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ResponseDialog(
    data: String,
    closeClick: () -> Unit
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        onDismissRequest = {}
    ) {
        Surface(
            elevation = 4.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = data)
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = closeClick
                ) {
                    Text(text = "Close")
                }
            }
        }
    }
}