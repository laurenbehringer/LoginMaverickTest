package com.example.loginmavericktest.domain.model


import com.google.gson.annotations.SerializedName

data class LoginMaverickModel(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("status")
    val status: String,
    @SerializedName("tokenType")
    val tokenType: String,
    @SerializedName("username")
    val username: String
)