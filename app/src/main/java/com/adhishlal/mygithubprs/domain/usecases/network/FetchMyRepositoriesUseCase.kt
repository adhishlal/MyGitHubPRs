package com.adhishlal.mygithubprs.domain.usecases.network

import com.adhishlal.mygithubprs.data.model.request.MyRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import com.adhishlal.mygithubprs.domain.usecases.base.CoroutineUseCaseWithParams
import javax.inject.Inject

class FetchMyRepositoriesUseCase @Inject constructor(private val repo: GitHubRepo) :
    CoroutineUseCaseWithParams<MyRepositoriesRequestModel, ArrayList<MyRepositoriesResponseModel>> {

    override suspend fun execute(params: MyRepositoriesRequestModel): ArrayList<MyRepositoriesResponseModel> {
        return repo.fetchMyRepositories(params)
    }
}
