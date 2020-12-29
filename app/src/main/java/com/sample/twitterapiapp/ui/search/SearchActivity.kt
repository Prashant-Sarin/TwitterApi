package com.sample.twitterapiapp.ui.search

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.twitterapiapp.R
import com.sample.twitterapiapp.databinding.ActivitySearchBinding
import com.sample.twitterapiapp.model.Feed
import com.sample.twitterapiapp.ui.adapter.FeedAdapter
import com.sample.twitterapiapp.ui.base.BaseActivity
import com.sample.twitterapiapp.util.NetworkUtil
import kotlinx.android.synthetic.main.activity_feed.rvFeed
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity() {

    private val TAG = SearchActivity::class.java.simpleName

    private var searchBinding: ActivitySearchBinding? = null
    private var viewModel: SearchViewModel? = null
    var feedsList = ArrayList<Feed>()
    var feedAdapter: FeedAdapter? = null
    var sortEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        viewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(SearchViewModel::class.java)
        searchBinding?.viewModel = viewModel
        searchBinding?.executePendingBindings()
        if (!NetworkUtil.isNetworkAvailable(this)) {
            showAlertDialog(
                getString(R.string.no_network),
                getString(R.string.try_after_sometime),
                getString(R.string.button_ok),
                DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                })
        }
        initUI()
        initObservers()
    }


    /**
     * Init UI in this function
     */
    private fun initUI() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.search)
        etSearch?.requestFocus()
    }


    /**
     * Init observers for viewModel liveData variables
     */
    private fun initObservers() {
        viewModel?.checked?.observe(this, Observer {
            sortEnabled = it
            populateFeeds()
        })

        viewModel?.queryText?.observe(this, Observer {
            viewModel?.getTweets(it, this)
        })

        viewModel?.feedApiSuccess?.observe(this, Observer {
            if (it) {
                viewModel?.feedList?.let { feeds ->
                    feedsList.clear()
                    feedsList.addAll(feeds)
                }
                populateFeeds()
            }
        })
    }


    /**
     * This function handles creating/ updating adapter for showing feeds in search result
     */
    private fun populateFeeds() {
        if (feedsList.isEmpty()) {
            tvEmpty?.visibility = View.VISIBLE
        } else {
            tvEmpty?.visibility = View.GONE
        }
        // check if sort is enabled
        if (sortEnabled) {
            viewModel?.getSortedList()?.let { sortedFeeds ->
                feedsList.clear()
                feedsList.addAll(sortedFeeds)
            }
        } else {
            viewModel?.feedList?.let { feeds ->
                feedsList.clear()
                feedsList.addAll(feeds)
            }
        }

        if (rvFeed.adapter == null) {
            feedAdapter = FeedAdapter(this, feedsList) { _, feed ->
                Log.d(TAG, "clicked feed ${feed.text}")
            }
            rvFeed.layoutManager = LinearLayoutManager(this)
            rvFeed.adapter = feedAdapter
        } else {
            rvFeed.adapter?.notifyDataSetChanged()
        }
    }


}