package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.os.Bundle
import android.view.View
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.databinding.ActivityPublicRepositoriesBinding
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import dagger.android.AndroidInjection

class PublicRepositoriesActivity : BaseActivity() {

    lateinit var publicRepositoriesBinding: ActivityPublicRepositoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        publicRepositoriesBinding = ActivityPublicRepositoriesBinding.inflate(layoutInflater)
        setContentView(publicRepositoriesBinding.root)

        val userId = intent.getStringExtra("userId") ?: ""

        publicRepositoriesBinding.noDataFound.errorText.text = getString(R.string.no_data_found)
        publicRepositoriesBinding.userRepoTitle.text = String.format(resources.getString(R.string.user_repositories), userId)

        callFunctionWithInternetCheck({ fetchPublicRepositories(userId, 1) },this)

        publicRepositoriesBinding.noDataFound.root.setOnClickListener {
            callFunctionWithInternetCheck({ fetchPublicRepositories(userId, 1) },this)
        }

        publicRepositoriesBinding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchPublicRepositories(userId: String, page: Int) {
        gitHubViewModel.getUserRepositories(userId, page)
        gitHubViewModel.userRepositoriesLiveData.observe(this) {
            publicRepositoriesBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                publicRepositoriesBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                publicRepositoriesBinding.repoList.visibility = View.GONE
                publicRepositoriesBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                publicRepositoriesBinding.repoList.visibility = View.VISIBLE
                publicRepositoriesBinding.noDataFound.root.visibility = View.GONE

                // todo populate data
            }
        }
    }
}