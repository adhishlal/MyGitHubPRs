package com.adhishlal.mygithubprs.presentation.base

import androidx.fragment.app.Fragment

abstract class DaggerBaseFragment<VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM

}
