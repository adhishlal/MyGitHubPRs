package com.adhishlal.mygithubprs.data.model.response

data class UserPullRequestsResponseModel(
    val title: String,
    val created_at: String,
    val closed_at: String,
    val state: String,
    val user: User
)

data class User(
    val avatar_url: String
)
