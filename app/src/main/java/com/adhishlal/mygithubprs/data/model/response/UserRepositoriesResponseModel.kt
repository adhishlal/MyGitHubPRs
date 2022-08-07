package com.adhishlal.mygithubprs.data.model.response

data class UserRepositoriesResponseModel(
    val name: String,
    val private: Boolean,
    val default_branch: String,
    val html_url: String,
    val owner: UserOwner,
)

data class UserOwner(
    val login: String
)

