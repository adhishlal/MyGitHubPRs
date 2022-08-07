package com.adhishlal.mygithubprs.data.model.request

import com.adhishlal.mygithubprs.BuildConfig
import com.adhishlal.mygithubprs.data.utils.Constants.DEFAULT_PAGE_SIZE

data class MyRepositoriesRequestModel(
    var per_page: Int = DEFAULT_PAGE_SIZE,
    var page: Int = 1,
    var authToken: String = BuildConfig.AuthToken
)
