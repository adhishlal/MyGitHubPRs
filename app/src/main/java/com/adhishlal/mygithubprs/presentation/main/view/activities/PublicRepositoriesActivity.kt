package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.data.model.response.UserRepositoriesResponseModel
import com.adhishlal.mygithubprs.data.utils.Constants.BLANK
import com.adhishlal.mygithubprs.data.utils.Constants.USER_ID
import com.adhishlal.mygithubprs.databinding.ActivityPublicRepositoriesBinding
import com.adhishlal.mygithubprs.presentation.main.view.adapter.UserRepositoryListAdapter
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubPublicRepoViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class PublicRepositoriesActivity : BaseActivity() {

    lateinit var publicRepositoriesBinding: ActivityPublicRepositoriesBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val gitHubPublicRepoViewModel: GitHubPublicRepoViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        publicRepositoriesBinding = ActivityPublicRepositoriesBinding.inflate(layoutInflater)
        setContentView(publicRepositoriesBinding.root)

        val userId = intent.getStringExtra(USER_ID) ?: BLANK

        publicRepositoriesBinding.noDataFound.errorText.text = getString(R.string.no_data_found)
        publicRepositoriesBinding.userRepoTitle.text =
            String.format(resources.getString(R.string.user_repositories), userId)

        callFunctionWithInternetCheck({ fetchPublicRepositories(userId, 1) }, this)

        publicRepositoriesBinding.noDataFound.root.setOnClickListener {
            callFunctionWithInternetCheck({ fetchPublicRepositories(userId, 1) }, this)
        }

        publicRepositoriesBinding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun fetchPublicRepositories(userId: String, page: Int) {
        publicRepositoriesBinding.progress.visibility = View.VISIBLE
        gitHubPublicRepoViewModel.getUserRepositories(userId, page)
        gitHubPublicRepoViewModel.userRepositoriesLiveData.observe(this) {
            publicRepositoriesBinding.progress.visibility = View.GONE

            if (it.isNullOrEmpty()) {
                publicRepositoriesBinding.noDataFound.errorText.text =
                    getString(R.string.no_data_found)
                publicRepositoriesBinding.repoList.visibility = View.GONE
                publicRepositoriesBinding.noDataFound.root.visibility = View.VISIBLE
            } else {
                publicRepositoriesBinding.repoList.visibility = View.VISIBLE
                publicRepositoriesBinding.noDataFound.root.visibility = View.GONE
                loadList(it)
            }
        }
    }

    private fun loadList(myList: ArrayList<UserRepositoriesResponseModel>) {
        publicRepositoriesBinding.repoList.apply {
            layoutManager = LinearLayoutManager(this@PublicRepositoriesActivity)
            adapter =
                UserRepositoryListAdapter(myList)
        }
    }
}