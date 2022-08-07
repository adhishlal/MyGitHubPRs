package com.adhishlal.mygithubprs.data.model.response

data class MyRepositoriesResponseModel(
    val name: String,
    val private: Boolean,
    val html_url: String,
)
