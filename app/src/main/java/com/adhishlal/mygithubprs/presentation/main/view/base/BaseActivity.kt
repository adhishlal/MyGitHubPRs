package com.adhishlal.mygithubprs.presentation.main.view.base

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.adhishlal.mygithubprs.domain.utils.isOnline
import com.adhishlal.mygithubprs.presentation.main.view.activities.NoInternetActivity
import com.adhishlal.mygithubprs.presentation.main.viewmodel.GitHubViewModel
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val gitHubViewModel: GitHubViewModel by viewModels { viewModelFactory }

    fun callFunctionWithInternetCheck(functionToCall: () -> Unit, context: Context) {
        if (isOnline(context)) {
            functionToCall()
        } else {
            val intent = Intent(this, NoInternetActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}