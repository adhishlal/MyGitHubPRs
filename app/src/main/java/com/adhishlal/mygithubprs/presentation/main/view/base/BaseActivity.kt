package com.adhishlal.mygithubprs.presentation.main.view.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.adhishlal.mygithubprs.domain.utils.isOnline
import com.adhishlal.mygithubprs.presentation.main.view.activities.NoInternetActivity

open class BaseActivity : AppCompatActivity() {

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