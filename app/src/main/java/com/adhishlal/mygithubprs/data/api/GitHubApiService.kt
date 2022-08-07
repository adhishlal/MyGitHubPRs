package com.adhishlal.mygithubprs.data.api

import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.model.response.UserResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET(EndPoints.MyDetails)
    suspend fun fetchMyDetails(@Header("Authorization") header: String): UserResponseModel

    @GET(EndPoints.MyRepositories)
    suspend fun fetchMyRepositories(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int,
        @Header("Authorization") header: String
    ): ArrayList<MyRepositoriesResponseModel>

    @GET(EndPoints.UserRepositories)
    suspend fun fetchUserRepositories(
        @Path("owner") owner: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): ArrayList<UserRepositoriesResponseModel>

    @GET(EndPoints.UserPRs)
    suspend fun fetchPullRequestsOfUser(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): ArrayList<UserPullRequestsResponseModel>

}
