package com.example.loginmavericktest.data.remote

import com.example.loginmavericktest.domain.model.LoginMaverickModel
import com.example.loginmavericktest.domain.request.LoginMaverickRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MaverickApi {

    @POST("login")
    suspend fun login(@Body data: LoginMaverickRequest): Response<LoginMaverickModel>

    companion object{
        const val BASE_URL = "https://8067-103-144-180-17.ap.ngrok.io/"
    }

}