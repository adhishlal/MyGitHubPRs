package com.adhishlal.mygithubprs.domain.di.base.injector

import android.app.Activity
import androidx.fragment.app.Fragment
import com.adhishlal.mygithubprs.domain.base.App
import dagger.android.DispatchingAndroidInjector

interface BaseModuleInjector {

    fun inject(application: App)

    fun activityInjector(): DispatchingAndroidInjector<Activity>

    fun fragmentInjector(): DispatchingAndroidInjector<Fragment>
}
