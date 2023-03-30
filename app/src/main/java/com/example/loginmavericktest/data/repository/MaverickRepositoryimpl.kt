package com.example.loginmavericktest.data.repository

import com.example.loginmavericktest.data.global_model.ResponseModel
import com.example.loginmavericktest.data.remote.MaverickApi
import com.example.loginmavericktest.domain.model.LoginMaverickModel
import com.example.loginmavericktest.domain.repository.MaverickRepository
import com.example.loginmavericktest.domain.request.LoginMaverickRequest
import javax.inject.Inject

class MaverickRepositoryimpl @Inject constructor(
    private val maverickApi: MaverickApi,
) : MaverickRepository {

    override suspend fun loginRedbank(data: LoginMaverickRequest): ResponseModel<LoginMaverickModel> {
        val result = ResponseModel<LoginMaverickModel>()
        println("Body : $data")
        val response = maverickApi.login(data)
        println("Response : $response")
        if (response.isSuccessful){
            result.data = response.body()
        } else {
            println("Error Code ${response.code()} \n Error Message : ${response.message()}")
            result.errorMessage = response.message()
        }
        return result
    }


}