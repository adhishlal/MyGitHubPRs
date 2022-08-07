package com.adhishlal.mygithubprs.domain.di.base.module

import com.adhishlal.mygithubprs.data.repository.GitHubRepositoryImpl
import com.adhishlal.mygithubprs.domain.di.scope.ModuleScope
import com.adhishlal.mygithubprs.domain.repository.GitHubRepo
import dagger.Binds
import dagger.Module

@Module(includes = [GitHubNetworkModule::class])
abstract class GitHubRepoModule {

    @Binds
    abstract fun provideRepo(gitHubRepositoryImpl: GitHubRepositoryImpl): GitHubRepo

}