package com.adhishlal.mygithubprs.domain.di.base.component

import com.adhishlal.mygithubprs.domain.di.base.injector.GitHubInjector
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubActivityBindingModule
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubRepoModule
import com.adhishlal.mygithubprs.domain.di.base.module.GitHubViewModelModule
import com.adhishlal.mygithubprs.domain.di.scope.ModuleScope
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class, GitHubActivityBindingModule::class, GitHubViewModelModule::class, GitHubRepoModule::class],
    dependencies = [AppComponent::class]
)

@ModuleScope
interface GitHubComponent {

    @Component.Builder
    interface Builder {
        fun create(appComponent: AppComponent): Builder
        fun build(): GitHubComponent
    }

    fun inject(injector: GitHubInjector)
}