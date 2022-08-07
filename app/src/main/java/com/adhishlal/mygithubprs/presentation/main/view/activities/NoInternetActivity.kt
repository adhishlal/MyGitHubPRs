package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.content.Intent
import android.os.Bundle
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.databinding.ActivityNoInternetBinding
import com.adhishlal.mygithubprs.domain.utils.isOnline
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import dagger.android.AndroidInjection

class NoInternetActivity : BaseActivity() {

    private lateinit var activityNoInternetBinding: ActivityNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        activityNoInternetBinding = ActivityNoInternetBinding.inflate(layoutInflater)
        setContentView(activityNoInternetBinding.root)

        checkInternetAndShowLayout()

        activityNoInternetBinding.tryAgain.setOnClickListener {
            checkInternetAndShowLayout()
        }
    }

    private fun checkInternetAndShowLayout() {
        if (isOnline(this)) {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        } else {
            activityNoInternetBinding.noInternet.errorText.text =
                getString(R.string.no_internet)
        }
    }
}
