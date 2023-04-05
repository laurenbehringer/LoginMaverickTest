package com.example.loginmavericktest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.loginmavericktest.Firebase.MyFirebaseInstance
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.log

@HiltAndroidApp
class LoginMaverickApp : Application()

