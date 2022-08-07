package com.adhishlal.mygithubprs.domain.usecases.network

import com.adhishlal.mygithubprs.data.model.response.UserResponseModel
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import com.adhishlal.mygithubprs.domain.usecases.base.CoroutineUseCaseWithParams
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repo: GitHubRepo) :
    CoroutineUseCaseWithParams<String, UserResponseModel> {

    override suspend fun execute(params: String): UserResponseModel = repo.getUserData(params)

}
