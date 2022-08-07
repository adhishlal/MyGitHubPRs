package com.adhishlal.mygithubprs.domain.di.base.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhishlal.mygithubprs.domain.base.GitHubViewModelFactory
import com.adhishlal.mygithubprs.domain.di.scope.ViewModelKey
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubDashboardViewModel
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubPublicRepoViewModel
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubPullRequestsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GitHubViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: GitHubViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GitHubDashboardViewModel::class)
    abstract fun bindGitHubDashboardViewModel(viewModel: GitHubDashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GitHubPublicRepoViewModel::class)
    abstract fun bindGitHubPublicRepoViewModel(viewModel: GitHubPublicRepoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GitHubPullRequestsViewModel::class)
    abstract fun bindGitHubPullRequestsViewModel(viewModel: GitHubPullRequestsViewModel): ViewModel
}
