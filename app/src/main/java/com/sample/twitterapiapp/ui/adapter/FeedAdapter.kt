package com.sample.twitterapiapp.ui.adapter

import android.content.Context
import android.net.Uri
import android.text.util.Linkify
import android.text.util.Linkify.TransformFilter
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.twitterapiapp.BR
import com.sample.twitterapiapp.R
import com.sample.twitterapiapp.databinding.ListItemFeedBinding
import com.sample.twitterapiapp.listeners.CustomItemClickListener
import com.sample.twitterapiapp.model.Feed
import java.util.regex.Pattern


class FeedAdapter(context: Context, var feedList: List<Feed>, val listener: (View, Feed) -> Unit) :
    RecyclerView.Adapter<FeedAdapter.FeedHolder>(), CustomItemClickListener {

    private val TAG = FeedAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedHolder {
        return FeedHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_feed,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = feedList.size

    override fun onBindViewHolder(holder: FeedHolder, position: Int) {
        val feed = feedList[position]
        holder.bindFeed(feed)
        Log.d(TAG, "feed = $feed")
        val imageUri = Uri.parse(feed.user?.profileImageURLHTTPS)
        holder.listItemFeedBinding.sdvFeeds.setImageURI(imageUri)
        if (feed.user?.verified == true) {
            holder.listItemFeedBinding.ivVerified.visibility = View.VISIBLE
        } else {
            holder.listItemFeedBinding.ivVerified.visibility = View.GONE
        }
        addLinks(holder, feed)
    }

    inner class FeedHolder(var listItemFeedBinding: ListItemFeedBinding) :
        RecyclerView.ViewHolder(listItemFeedBinding.root) {
        fun bindFeed(feed: Feed) {
            listItemFeedBinding.setVariable(BR.feedModel, feed)
            listItemFeedBinding.setVariable(BR.itemClickListener, this@FeedAdapter)
            listItemFeedBinding.executePendingBindings()
        }
    }

    override fun <T> onItemClick(view: View, t: T) {
        val feed = t as Feed
        listener(view, feed)
    }

    /**
     * This method adds links to text under feed object
     */
    private fun addLinks(holder: FeedHolder, feed: Feed) {
        val filter = TransformFilter { match, url -> match.group() }

        val mentionPattern: Pattern = Pattern.compile("@([A-Za-z0-9_-]+)")
        val mentionScheme = "http://www.twitter.com/"
        Linkify.addLinks(
            holder.listItemFeedBinding.tvText,
            mentionPattern,
            mentionScheme,
            null,
            filter
        )

        val hashtagPattern: Pattern = Pattern.compile("#([A-Za-z0-9_-]+)")
        val hashtagScheme = "http://www.twitter.com/search/"
        Linkify.addLinks(
            holder.listItemFeedBinding.tvText,
            hashtagPattern,
            hashtagScheme,
            null,
            filter
        )

        val urlPattern: Pattern = Patterns.WEB_URL
        Linkify.addLinks(holder.listItemFeedBinding.tvText, urlPattern, null, null, filter)
    }

}