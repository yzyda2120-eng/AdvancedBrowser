package com.example.advancedbrowser

import android.content.Context
import android.webkit.WebResourceResponse
import java.io.ByteArrayInputStream
import java.net.URL

class AdBlocker(private val context: Context) {
    private val adDomains = hashSetOf(
        "doubleclick.net",
        "googleadservices.com",
        "googlesyndication.com",
        "adservice.google.com",
        "adnxs.com",
        "ads.youtube.com",
        "ad.doubleclick.net",
        "ads-at-google.com",
        "amazon-adsystem.com",
        "analytics.google.com",
        "facebook.com/tr",
        "connect.facebook.net",
        "pixel.facebook.com"
    )

    fun shouldBlock(url: String): Boolean {
        return try {
            val host = URL(url).host
            adDomains.any { host.contains(it) }
        } catch (e: Exception) {
            false
        }
    }

    fun createEmptyResponse(): WebResourceResponse {
        return WebResourceResponse("text/plain", "UTF-8", ByteArrayInputStream("".toByteArray()))
    }
}
