package com.adhishlal.mygithubprs.domain.di.base.module

import android.app.Application
import dagger.Module

@Module(includes = [GitHubViewModelModule::class])
class AppModule(private val app: Application) {
}