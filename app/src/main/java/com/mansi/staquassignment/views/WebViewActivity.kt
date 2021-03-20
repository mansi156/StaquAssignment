package com.mansi.staquassignment.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mansi.staquassignment.R
import com.mansi.staquassignment.databinding.ActivityWebViewBinding
import com.mansi.staquassignment.utility.Common

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityWebViewBinding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(activityWebViewBinding.root)
        setSupportActionBar(activityWebViewBinding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        val pageLink = intent.getStringExtra(Common.PAGE_LINK).toString()
        activityWebViewBinding.stackpageWebview.loadUrl(pageLink)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}