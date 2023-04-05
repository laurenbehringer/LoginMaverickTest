package com.example.loginmavericktest.Firebase

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.platform.*
import NotificationService
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject

class MyFirebaseInstance : FirebaseMessagingService() {
    val TAG = "PushNotifService"

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        val shared: SharedPreferences = applicationContext.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = shared.edit()
        editor.remove("token").apply()
        editor.putString("token", newToken)
        editor.commit()
        Log.d(TAG + "Token Here ", newToken)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "Dikirim dari : ${message.from}")
        Log.d(TAG, "Notification : ${message.data}")
        val notificationService = NotificationService(applicationContext)
        if (message.data.isNotEmpty()) {
            notificationService.showFirebaseNotification(message)
        }
    }
    


//    fun getToken(): String {
//        var token = "kjhkjhkjhk";
//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//
//            token = task.result
//
//            // Log
//            print("Token here $token")
//            Log.d(TAG + "Token Here ", token)
//        })
//
//
////        Log.d("BROOOOO", )
//        return token;
//    }

}

