package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.UserPullRequestsResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.data.utils.Constants.REPO
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS
import com.adhishlal.mygithubprs.data.utils.Constants.STATUS_CLOSED
import com.adhishlal.mygithubprs.data.utils.Constants.USER_ID
import com.adhishlal.mygithubprs.databinding.ActivityPullRequestsBinding
import com.adhishlal.mygithubprs.presentation.main.view.adapter.UserPRListAdapter
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubPullRequestsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class PullRequestsActivity : BaseActivity() {

    lateinit var activityPullRequestsBinding: ActivityPullRequestsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val gitHubPullRequestsViewModel: GitHubPullRequestsViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        activityPullRequestsBinding = ActivityPullRequestsBinding.inflate(layoutInflater)
        setContentView(activityPullRequestsBinding.root)

        val userId = intent.getStringExtra(USER_ID) ?: BLANK
        val status = intent.getStringExtra(STATUS) ?: STATUS_CLOSED
        val repo = intent.getStringExtra(REPO) ?: BLANK

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
        activityPullRequestsBinding.progress.visibility = View.VISIBLE
        gitHubPullRequestsViewModel.getPRList(userId, repo, page)
        gitHubPullRequestsViewModel.prListLiveData.observe(this) {
            activityPullRequestsBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                activityPullRequestsBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                activityPullRequestsBinding.repoList.visibility = View.GONE
                activityPullRequestsBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                activityPullRequestsBinding.repoList.visibility = View.VISIBLE
                activityPullRequestsBinding.noDataFound.root.visibility = View.GONE
                loadList(it)
            }
        }
    }

    private fun loadList(myList: ArrayList<UserPullRequestsResponseModel>) {
        activityPullRequestsBinding.repoList.apply {
            layoutManager = LinearLayoutManager(this@PullRequestsActivity)
            adapter =
                UserPRListAdapter(myList)
        }
    }
}