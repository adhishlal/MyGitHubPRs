package com.adhishlal.mygithubprs.presentation.base

import androidx.appcompat.app.AppCompatActivity

abstract class DaggerBaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    abstract val viewModel: VM
}