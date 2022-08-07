package com.adhishlal.mygithubprs.domain.di.base.injector

import android.app.Activity
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import com.adhishlal.mygithubprs.domain.base.App
import com.adhishlal.mygithubprs.domain.base.ProjectApp
import com.adhishlal.mygithubprs.domain.di.base.component.DaggerGitHubComponent
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

@Keep
class GitHubInjector : BaseModuleInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun inject(application: App) {
        DaggerGitHubComponent.builder()
            .create(ProjectApp.provideAppComponent())
            .build()
            .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun fragmentInjector(): DispatchingAndroidInjector<Fragment> = fragmentInjector
}
