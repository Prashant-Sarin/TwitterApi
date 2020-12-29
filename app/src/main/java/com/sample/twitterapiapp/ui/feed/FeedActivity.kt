package com.sample.twitterapiapp.ui.feed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.twitterapiapp.R
import com.sample.twitterapiapp.applicationtwitter.prefs.feeds
import com.sample.twitterapiapp.applicationtwitter.prefs.preferences
import com.sample.twitterapiapp.applicationtwitter.prefs.username
import com.sample.twitterapiapp.databinding.ActivityFeedBinding
import com.sample.twitterapiapp.model.Feed
import com.sample.twitterapiapp.ui.adapter.FeedAdapter
import com.sample.twitterapiapp.ui.base.BaseActivity
import com.sample.twitterapiapp.ui.search.SearchActivity
import com.sample.twitterapiapp.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : BaseActivity() {

    private val TAG = FeedActivity::class.java.simpleName

    var activityFeedBinding: ActivityFeedBinding? = null
    var viewModel: FeedViewModel? = null
    var feedsList = ArrayList<Feed>()
    var feedAdapter: FeedAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFeedBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(FeedViewModel::class.java)
        initUI()
        initObservers()
        if (!NetworkUtil.isNetworkAvailable(this)) {
            feedsList = preferences.feeds
            Log.d(TAG, "preference feeds = $feedsList")
            setupfeeds()
        }
        viewModel?.getFeeds(this)
        swipeLayout.setOnRefreshListener {
            viewModel?.getFeeds(this)
        }
    }

    override fun onResume() {
        super.onResume()
        feedsList = preferences.feeds
        setupfeeds()
        viewModel?.getFeeds(this)
    }

    /**
     * Init UI in this function
     */
    private fun initUI() {
        supportActionBar?.title = getString(R.string.tweets)
        supportActionBar?.subtitle = getString(R.string.for_user, preferences.username)

        fabSearch?.setOnClickListener {
            navigateToSearchActivity()
        }
    }


    /**
     * Init observers for viewModel liveData variables
     */
    private fun initObservers() {
        viewModel?.feedApiSuccess?.observe(this, Observer {
            if (swipeLayout.isRefreshing) {
                swipeLayout.isRefreshing = false
            }
            if (it) {
                viewModel?.feedList?.let { feeds ->
                    feedsList = feeds
                    preferences.feeds = feeds
                }
                setupfeeds()
            }
        })
    }


    /**
     * This function handles creating/ updating adapter for loading feeds
     */
    private fun setupfeeds() {
        if (feedAdapter == null) {
            feedAdapter = FeedAdapter(this, feedsList) { _, feed ->
                Log.d(TAG, "clicked feed ${feed.text}")
            }
            rvFeed.layoutManager = LinearLayoutManager(this)
            rvFeed.adapter = feedAdapter
        } else {
            feedAdapter?.notifyDataSetChanged()
        }
    }

    /**
     * Navigates to SearchActivity
     */
    private fun navigateToSearchActivity() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

}