package com.example.loginmavericktest.domain.request


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cifnum")
    val cifnum: String,
    @SerializedName("click_action")
    val clickAction: String,
    @SerializedName("navigations")
    val navigations: String,
    @SerializedName("username")
    val username: String
)