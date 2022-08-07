package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.os.Bundle
import android.view.View
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS_CLOSED
import com.adhishlal.mygithubprs.databinding.ActivityPullRequestsBinding
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import dagger.android.AndroidInjection

class PullRequestsActivity : BaseActivity() {

    lateinit var activityPullRequestsBinding: ActivityPullRequestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        activityPullRequestsBinding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(activityPullRequestsBinding.root)

        val userId = intent.getStringExtra("userId") ?: BLANK
        val status = intent.getStringExtra("status") ?: STATUS_CLOSED
        val repo = intent.getStringExtra("repo") ?: BLANK

        activityPullRequestsBinding.noDataFound.errorText.text = getString(R.string.no_data_found)
        activityPullRequestsBinding.userPRs.text =
            String.format(resources.getString(R.string.pull_requests), status, userId, repo)

        callFunctionWithInternetCheck({ fetchPullRequests(userId, repo, 1) }, this)

        activityPullRequestsBinding.noDataFound.root.setOnClickListener {
            callFunctionWithInternetCheck({ fetchPullRequests(userId, repo, 1) }, this)
        }

        activityPullRequestsBinding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchPullRequests(userId: String, repo: String, page: Int) {
        gitHubViewModel.getPRList(userId, repo, page)
        gitHubViewModel.prListLiveData.observe(this) {
            activityPullRequestsBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                activityPullRequestsBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                activityPullRequestsBinding.repoList.visibility = View.GONE
                activityPullRequestsBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                activityPullRequestsBinding.repoList.visibility = View.VISIBLE
                activityPullRequestsBinding.noDataFound.root.visibility = View.GONE

                // todo populate data
            }
        }
    }
}