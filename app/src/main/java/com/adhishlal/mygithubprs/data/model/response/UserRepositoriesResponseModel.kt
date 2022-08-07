package com.adhishlal.mygithubprs.data.model.response

data class UserRepositoriesResponseModel(
    val name: String,
    val private: Boolean,
    val html_url: String,
)
