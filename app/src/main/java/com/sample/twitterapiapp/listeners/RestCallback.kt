package com.sample.twitterapiapp.listeners

interface RestCallback<T> {
    fun onSuccess(t:T?)
    fun onFailure(error: String)
}