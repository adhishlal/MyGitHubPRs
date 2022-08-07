package com.adhishlal.mygithubprs.domain.di.base.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhishlal.mygithubprs.domain.base.GitHubViewModelFactory
import com.adhishlal.mygithubprs.domain.di.scope.ViewModelKey
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GitHubViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: GitHubViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GitHubViewModel::class)
    abstract fun bindGitHubViewModel(viewModel: GitHubViewModel): ViewModel
}
