package com.sample.twitterapiapp.listeners

import android.view.View

interface CustomItemClickListener {
    fun <T> onItemClick(view: View, t: T)
}