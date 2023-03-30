package com.example.loginmavericktest.domain.usecase

import com.example.loginmavericktest.domain.model.LoginMaverickModel
import com.example.loginmavericktest.domain.repository.MaverickRepository
import com.example.loginmavericktest.domain.request.LoginMaverickRequest
import com.example.loginmavericktest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginMaverickUseCase @Inject constructor(
    private val repository: MaverickRepository
) {
    operator fun invoke(data: LoginMaverickRequest): Flow<Resource<LoginMaverickModel>> = flow {
        emit(Resource.Loading(true))
        val response = repository.loginRedbank(data)
        if (response.data != null){
            if (response.data != null){
                emit(Resource.Success(data = response.data))
            } else {
                emit(Resource.Error(data = response.data))
            }
        } else {
            emit(Resource.Error(message = response.errorMessage))
        }
        emit(Resource.Loading(false))
    }
}