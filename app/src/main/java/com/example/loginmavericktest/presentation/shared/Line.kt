package com.example.loginmavericktest.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Line() {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
        .height(0.7.dp)
        .background(Color.LightGray))
}