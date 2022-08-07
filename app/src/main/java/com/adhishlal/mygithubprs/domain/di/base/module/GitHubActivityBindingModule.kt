package com.adhishlal.mygithubprs.domain.di.base.module

import com.adhishlal.mygithubprs.domain.di.scope.ActivityScope
import com.adhishlal.mygithubprs.presentation.main.view.activities.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GitHubActivityBindingModule {

    @ContributesAndroidInjector(modules = [GitHubFragmentBindingModule::class])
    @ActivityScope
    abstract fun provideDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun provideNoInternetActivity(): NoInternetActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun provideUnauthorizedActivity(): UnauthorizedActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun providePublicRepositoriesActivity(): PublicRepositoriesActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun providePullRequestsActivity(): PullRequestsActivity

}
