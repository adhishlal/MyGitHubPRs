package com.adhishlal.mygithubprs.domain.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.adhishlal.mygithubprs.domain.di.base.component.AppComponent
import com.adhishlal.mygithubprs.domain.di.base.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class ProjectApp : App(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentDispatchAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    companion object {

        lateinit var appComponent: AppComponent

        fun provideAppComponent() = appComponent
    }

    private val moduleActivityInjectors = mutableListOf<DispatchingAndroidInjector<Activity>>()
    private val moduleFragmentInjectors = mutableListOf<DispatchingAndroidInjector<Fragment>>()

    private val activityInjector = AndroidInjector<Activity> { instance ->
        if (activityDispatchingAndroidInjector.maybeInject(instance)) {
            return@AndroidInjector
        }

        moduleActivityInjectors.forEach { injector ->
            if (injector.maybeInject(instance)) {
                return@AndroidInjector
            }
        }
        throw IllegalStateException("Injector not found for $instance")
    }

    private val fragmentInjector = AndroidInjector<Fragment> { instance ->
        if (fragmentDispatchAndroidInjector.maybeInject(instance)) {
            return@AndroidInjector
        }

        moduleFragmentInjectors.forEach { injector ->
            if (injector.maybeInject(instance)) {
                return@AndroidInjector
            }
        }
        throw IllegalStateException("Injector not found for $instance")
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
