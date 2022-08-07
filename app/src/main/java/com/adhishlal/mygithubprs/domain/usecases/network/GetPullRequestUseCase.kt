package com.adhishlal.mygithubprs.domain.usecases.network

import com.adhishlal.mygithubprs.data.model.request.UserPullRequestModel
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import com.adhishlal.mygithubprs.domain.usecases.base.CoroutineUseCaseWithParams
import javax.inject.Inject

class GetPullRequestUseCase @Inject constructor(private val repo: GitHubRepo) :
    CoroutineUseCaseWithParams<UserPullRequestModel, ArrayList<UserPullRequestsResponseModel>> {

    override suspend fun execute(params: UserPullRequestModel): ArrayList<UserPullRequestsResponseModel> {
        return repo.getUserPullRequests(params)
    }
}
