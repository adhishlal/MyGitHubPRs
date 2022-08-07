package com.adhishlal.mygithubprs.domain.repository

import com.adhishlal.mygithubprs.data.model.request.MyRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserPullRequestModel
import com.adhishlal.mygithubprs.data.model.request.UserRepositoriesRequestModel
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserResponseModel

interface GitHubRepo {

    suspend fun getUserData(authToken: String): UserResponseModel
    suspend fun getUserPullRequests(userPullRequestModel: UserPullRequestModel): ArrayList<UserPullRequestsResponseModel>
    suspend fun fetchMyRepositories(myRepositoriesRequestModel: MyRepositoriesRequestModel): ArrayList<MyRepositoriesResponseModel>
    suspend fun fetchUserRepositories(userRepositoriesRequestModel: UserRepositoriesRequestModel): ArrayList<UserRepositoriesResponseModel>

}
