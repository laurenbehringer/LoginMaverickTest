package com.example.loginmavericktest.domain.request


import com.google.gson.annotations.SerializedName

data class LoginMaverickRequest(
    @SerializedName("data")
    val `data`: Data = Data(
       navigations = "verification",
        clickAction =  "FLUTTER_NOTIFICATION_CLICK",
        username = "mod1",
        cifnum = "13010808955"
    ),
    @SerializedName("password")
    val password: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)