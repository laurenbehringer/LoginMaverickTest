package com.example.loginmavericktest.domain.request

data class LoginMaverickRequest(
    val data: Data = Data(
       navigations = "verification",
       click_action =  "FLUTTER_NOTIFICATION_CLICK",
       username = "mod1",
       cifnum = "13010808955"
    ),
    val password: String,
    val token: String,
    val username: String
)