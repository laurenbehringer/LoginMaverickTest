package com.example.loginmavericktest.data.global_model

data class ResponseModel<T>(
    var data: T? = null,
    var errorMessage: String? = null
)

