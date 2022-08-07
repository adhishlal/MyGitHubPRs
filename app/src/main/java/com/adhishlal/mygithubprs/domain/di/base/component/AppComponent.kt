package com.adhishlal.mygithubprs.domain.di.base.component

import android.app.Application
import com.adhishlal.mygithubprs.domain.base.ProjectApp
import com.adhishlal.mygithubprs.domain.di.base.module.AppModule
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubActivityBindingModule
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubNetworkModule
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubRepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, GitHubActivityBindingModule::class, GitHubNetworkModule::class, AndroidInjectionModule::class, AppModule::class, GitHubRepoModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun provideApplication(): Application
    fun inject(app: ProjectApp)
}