package com.sample.twitterapiapp.ui.login

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.twitterapiapp.BR
import com.sample.twitterapiapp.R
import com.sample.twitterapiapp.applicationtwitter.AppConstants
import com.sample.twitterapiapp.databinding.ActivityLoginBinding
import com.sample.twitterapiapp.ui.base.BaseActivity
import com.sample.twitterapiapp.ui.feed.FeedActivity
import twitter4j.Twitter
import twitter4j.auth.RequestToken


class LoginActivity : BaseActivity() {

    private val TAG = LoginActivity::class.java.simpleName

    private var viewModel: LoginViewModel? = null
    private var activityLoginBinding: ActivityLoginBinding? = null

    companion object {
        // Twitter
        var twitter: Twitter? = null
        var requestToken: RequestToken? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(LoginViewModel::class.java)
        activityLoginBinding?.setVariable(BR.loginViewModel, viewModel)
        activityLoginBinding?.setVariable(BR.context, this)
        activityLoginBinding?.executePendingBindings()

        // Check if twitter keys are set
        if (AppConstants.TWITTER_CONSUMER_KEY.trim()
                .isEmpty() || AppConstants.TWITTER_CONSUMER_SECRET.trim().isEmpty()
        ) {
            // Internet Connection is not present
            showAlertDialog("Twitter oAuth tokens",
                "Please set your twitter oauth tokens first!",
                "OK",
                DialogInterface.OnClickListener { dialogInterface, _ ->
                    dialogInterface.dismiss()
                })
            // stop executing code by return
            return
        }

        if (viewModel?.isTwitterLoggedInAlready(this) == true) {
            navigateToFeedActivity()
        }
        initObservers()
        viewModel?.checkForAlreadyLoggedIn(this, intent)
    }

    /**
     * Initializing observers for liveData
     */
    private fun initObservers() {
        viewModel?.isLoggedIn?.observe(this, Observer {
            if (it) {
                navigateToFeedActivity()
            }
        })
    }

    /**
     * Navigates to FeedActivity
     */
    private fun navigateToFeedActivity() {
        val intent = Intent(this, FeedActivity::class.java)
        startActivity(intent)
        finish()
    }

}