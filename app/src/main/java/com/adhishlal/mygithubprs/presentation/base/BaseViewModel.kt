package com.adhishlal.mygithubprs.presentation.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adhishlal.mygithubprs.domain.base.App
import com.adhishlal.mygithubprs.presentation.main.view.activities.UnauthorizedActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import retrofit2.HttpException
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel : ViewModel() {

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        handleException(throwable)
    }

    private fun handleException(throwable: Throwable) {
        if (throwable is HttpException) {
            when (throwable.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                    openUnauthorizedScreen()
                    viewModelScope.coroutineContext.cancel()
                }
            }
        }
    }

    private fun openUnauthorizedScreen() {
        val intent = Intent(App.appContext, UnauthorizedActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        App.appContext.startActivity(intent)
    }
}
