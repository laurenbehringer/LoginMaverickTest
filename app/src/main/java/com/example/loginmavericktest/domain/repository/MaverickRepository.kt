package com.example.loginmavericktest.domain.repository

import com.example.loginmavericktest.data.global_model.ResponseModel
import com.example.loginmavericktest.domain.model.LoginMaverickModel
import com.example.loginmavericktest.domain.request.LoginMaverickRequest

interface MaverickRepository {


    suspend fun loginRedbank(
        data: LoginMaverickRequest
    ): ResponseModel<LoginMaverickModel>


}