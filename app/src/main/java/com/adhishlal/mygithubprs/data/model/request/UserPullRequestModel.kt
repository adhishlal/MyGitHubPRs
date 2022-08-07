package com.adhishlal.mygithubprs.data.model.request

import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.data.utils.Constants.DEFAULT_PAGE_SIZE
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS_CLOSED

data class UserPullRequestModel(
    var userId: String = BLANK,
    var repo: String = BLANK,
    var state: String = STATUS_CLOSED,
    var perPage: Int = DEFAULT_PAGE_SIZE,
    var page: Int = 1
)