package com.adhishlal.mygithubprs.domain.di.base.module

import com.adhishlal.mygithubprs.presentation.main.view.fragments.FragmentUserName
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GitHubFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideFragmentUserName(): FragmentUserName
}