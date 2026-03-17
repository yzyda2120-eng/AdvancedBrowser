package com.example.advancedbrowser

import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient

class BrowserWebViewClient(private val adBlocker: AdBlocker) : WebViewClient() {

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse? {
        val url = request?.url.toString()
        
        // Anti-Tracking and Ad-Blocker Logic
        if (adBlocker.shouldBlock(url)) {
            return adBlocker.createEmptyResponse()
        }
        
        return super.shouldInterceptRequest(view, request)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}
