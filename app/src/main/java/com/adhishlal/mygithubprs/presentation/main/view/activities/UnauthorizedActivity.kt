package com.adhishlal.mygithubprs.presentation.main.view.activities

import android.content.Intent
import android.os.Bundle
import com.adhishlal.mygithubprs.R
import com.adhishlal.mygithubprs.databinding.ActivityUnauthorizedBinding
import com.adhishlal.mygithubprs.presentation.main.view.base.BaseActivity
import com.adhishlal.mygithubprs.presentation.main.view.bottomsheets.UserNameBottomSheet
import dagger.android.AndroidInjection

class UnauthorizedActivity : BaseActivity() {

    lateinit var unauthorizedActivityBinding: ActivityUnauthorizedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        unauthorizedActivityBinding = ActivityUnauthorizedBinding.inflate(layoutInflater)
        setContentView(unauthorizedActivityBinding.root)

        unauthorizedActivityBinding.unauthorized.errorText.text = getString(R.string.logged_out)

        unauthorizedActivityBinding.publicRepo.setOnClickListener {
            val bottomSheet = UserNameBottomSheet()
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        unauthorizedActivityBinding.login.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}