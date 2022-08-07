package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhishlal.mygithubprs.BuildConfig
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.databinding.ActivityDashboardBinding
import com.adhishlal.mygithubprs.presentation.main.view.adapter.RepositoryListAdapter
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection

class DashboardActivity : BaseActivity() {

    private lateinit var activityDashboardBinding: ActivityDashboardBinding

    private lateinit var myRepositoriesResponseModel: MyRepositoriesResponseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        activityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)

        setup()

        callFunctionWithInternetCheck({ fetchMyUserData(BuildConfig.AuthToken) }, this)
        callFunctionWithInternetCheck({ fetchMyRepositories() }, this)

        activityDashboardBinding.publicUserLayout.setOnClickListener {
            // todo open this from bottom sheet
            val intent = Intent(this, PublicRepositoriesActivity::class.java)
            intent.putExtra("userId", "google")
            startActivity(intent)
        }

        activityDashboardBinding.noDataFound.root.setOnClickListener {
            callFunctionWithInternetCheck({ fetchMyRepositories() }, this)
        }

        activityDashboardBinding.logout.setOnClickListener {
            callFunctionWithInternetCheck({ fetchMyUserData(BLANK) }, this)
        }


    }

    private fun setup() {
        val version =
            String.format(resources.getString(R.string.app_version, BuildConfig.VERSION_NAME))
        activityDashboardBinding.versionText.text = version
    }

    private fun loadList() {
        activityDashboardBinding.repoList.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = RepositoryListAdapter(myRepositoriesResponseModel)
        }
    }

    private fun fetchMyUserData(authToken: String) {
        activityDashboardBinding.progress.visibility = View.VISIBLE
        gitHubViewModel.getUserData(authToken)
        gitHubViewModel.userDataResponseLiveData.observe(this) {

            activityDashboardBinding.progress.visibility = View.GONE

            val name = String.format(resources.getString(R.string.welcome_text, it.login))
            activityDashboardBinding.welcomeText.text = name
            Picasso.get()
                .load(it.avatar_url)
                .resize(100, 100)
                .centerCrop()
                .placeholder(R.drawable.github)
                .error(R.drawable.github)
                .into(activityDashboardBinding.githubAvatar)
        }
    }

    private fun fetchMyRepositories() {

        activityDashboardBinding.progress.visibility = View.VISIBLE

        gitHubViewModel.getMyRepositories(1)
        gitHubViewModel.myRepositoriesLiveData.observe(this) {

            activityDashboardBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                activityDashboardBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                activityDashboardBinding.repoList.visibility = View.GONE
                activityDashboardBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                activityDashboardBinding.repoList.visibility = View.VISIBLE
                activityDashboardBinding.noDataFound.root.visibility = View.GONE
                loadList()
            }
        }
    }

    private fun fetchPRList(userId: String, repo: String, page: Int) {
        activityDashboardBinding.progress.visibility = View.VISIBLE

        gitHubViewModel.getPRList(userId, repo, page)
        gitHubViewModel.prListLiveData.observe(this) {

            activityDashboardBinding.progress.visibility = View.GONE
        }
    }

    private fun fetchUserRepositories(userId: String, page: Int) {
        activityDashboardBinding.progress.visibility = View.VISIBLE

        gitHubViewModel.getUserRepositories(userId, page)
        gitHubViewModel.userRepositoriesLiveData.observe(this) {

            activityDashboardBinding.progress.visibility = View.GONE
        }
    }
}
