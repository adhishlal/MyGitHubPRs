package com.adhishlal.mygithubprs.presentation.main.view.fragments

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.adhishlal.mygithubprs.presentation.base.DaggerBaseFragment
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubViewModel
import javax.inject.Inject

class FragmentUserName : DaggerBaseFragment<GitHubViewModel>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel: GitHubViewModel by activityViewModels { viewModelFactory }
}