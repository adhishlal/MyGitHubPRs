package com.adhishlal.mygithubprs.data.model.request

import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.data.utils.Constants.DEFAULT_PAGE_SIZE

data class UserRepositoriesRequestModel(
    var owner: String = BLANK,
    var per_page: Int = DEFAULT_PAGE_SIZE,
    var page: Int = 1
)
