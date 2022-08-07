package com.adhishlal.mygithubprs.domain.usecases.network

import com.adhishlal.mygithubprs.data.model.request.UserRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import com.adhishlal.mygithubprs.domain.usecases.base.CoroutineUseCaseWithParams
import javax.inject.Inject

class FetchUserRepositoriesUseCase @Inject constructor(private val repo: GitHubRepo) :
    CoroutineUseCaseWithParams<UserRepositoriesRequestModel, ArrayList<UserRepositoriesResponseModel>> {

    override suspend fun execute(params: UserRepositoriesRequestModel): ArrayList<UserRepositoriesResponseModel> {
        return repo.fetchUserRepositories(params)
    }
}
