package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhishlal.mygithubprs.BuildConfig
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.MyRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.databinding.ActivityDashboardBinding
import com.adhishlal.mygithubprs.presentation.main.view.adapter.RepositoryListAdapter
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import com.adhishlal.mygithubprs.presentation.main.view.bottomsheets.UserNameBottomSheet
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubDashboardViewModel
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import javax.inject.Inject

class DashboardActivity : BaseActivity() {

    private lateinit var activityDashboardBinding: ActivityDashboardBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val gitHubDashboardViewModel: GitHubDashboardViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        activityDashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(activityDashboardBinding.root)

        setup()

        callFunctionWithInternetCheck({ fetchMyUserData(BuildConfig.AuthToken) }, this)
        callFunctionWithInternetCheck({ fetchMyRepositories() }, this)

        activityDashboardBinding.publicUserLayout.setOnClickListener {
            val bottomSheet = UserNameBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
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

    private fun loadList(myList: ArrayList<MyRepositoriesResponseModel>) {
        activityDashboardBinding.repoList.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter =
                RepositoryListAdapter(myList)
        }
    }

    private fun fetchMyUserData(authToken: String) {
        activityDashboardBinding.progress.visibility = View.VISIBLE
        gitHubDashboardViewModel.getUserData(authToken)
        gitHubDashboardViewModel.userDataResponseLiveData.observe(this) {

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

        gitHubDashboardViewModel.getMyRepositories(1)
        gitHubDashboardViewModel.myRepositoriesLiveData.observe(this) {

            activityDashboardBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                activityDashboardBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                activityDashboardBinding.repoList.visibility = View.GONE
                activityDashboardBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                activityDashboardBinding.repoList.visibility = View.VISIBLE
                activityDashboardBinding.noDataFound.root.visibility = View.GONE
                loadList(it)
            }
        }
    }
}
