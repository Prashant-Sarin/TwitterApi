package com.sample.twitterapiapp.ui.base

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.sample.twitterapiapp.R
import com.sample.twitterapiapp.util.NetworkUtil

open class BaseActivity: AppCompatActivity() {
    private val TAG = BaseActivity::class.java.simpleName

    var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // we can initialize something here, that needs to be initialized for all activities
    }

    /**
     * shows alert dialog
     * @param title: The title of alert dialog
     * @param message: The message of alert dialog
     * @param buttonText: The text for positive Button button
     * @param positiveListener: The listener for positive button
     */
    fun showAlertDialog(title: String, message: String,buttonText: String, positiveListener: DialogInterface.OnClickListener){
        if (alertDialog!=null && alertDialog?.isShowing == true){
            alertDialog?.dismiss()
        }
        alertDialog = AlertDialog.Builder(this).create()
        alertDialog?.setTitle(title)
        alertDialog?.setMessage(message)
        alertDialog?.setButton(AlertDialog.BUTTON_POSITIVE,buttonText, positiveListener)
        alertDialog?.setCancelable(false)
        alertDialog?.show()
    }
}