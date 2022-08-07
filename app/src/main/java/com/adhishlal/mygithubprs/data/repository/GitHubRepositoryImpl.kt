package com.adhishlal.mygithubprs.data.repository

import com.adhishlal.mygithubprs.data.api.GitHubApiService
import com.adhishlal.mygithubprs.data.model.request.MyRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserPullRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserResponseModel
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(
    private val gitHubApiService: GitHubApiService
) : GitHubRepo {

    override suspend fun getUserData(authToken: String): UserResponseModel =
        gitHubApiService.fetchMyDetails(authToken)

    override suspend fun getUserPullRequests(userPullRequestModel: UserPullRequestModel): ArrayList<UserPullRequestsResponseModel> =
        gitHubApiService.fetchPullRequestsOfUser(
            userPullRequestModel.userId,
            userPullRequestModel.repo,
            userPullRequestModel.state,
            userPullRequestModel.perPage,
            userPullRequestModel.page
        )

    override suspend fun fetchMyRepositories(myRepositoriesRequestModel: MyRepositoriesRequestModel): ArrayList<MyRepositoriesResponseModel> =
        gitHubApiService.fetchMyRepositories(
            myRepositoriesRequestModel.per_page,
            myRepositoriesRequestModel.page,
            myRepositoriesRequestModel.authToken
        )

    override suspend fun fetchUserRepositories(userRepositoriesRequestModel: UserRepositoriesRequestModel): ArrayList<UserRepositoriesResponseModel> =
        gitHubApiService.fetchUserRepositories(
            userRepositoriesRequestModel.owner,
            userRepositoriesRequestModel.per_page,
            userRepositoriesRequestModel.page
        )
}
