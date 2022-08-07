package com.adhishlal.mygithubprs.data.model.response

data class MyRepositoriesResponseModel(
    val name: String,
    val private: Boolean,
    val default_branch: String,
    val html_url: String,
    val owner: Owner,
)

data class Owner(
    val login: String
)
