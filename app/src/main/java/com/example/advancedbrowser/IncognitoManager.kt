package com.example.advancedbrowser

import android.content.Context
import android.webkit.CookieManager
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewDatabase

class IncognitoManager(private val context: Context) {

    fun clearAllData() {
        // Clear Cookies
        val cookieManager = CookieManager.getInstance()
        cookieManager.removeAllCookies(null)
        cookieManager.flush()

        // Clear WebStorage (localStorage, databases)
        WebStorage.getInstance().deleteAllData()

        // Clear WebView Database (form data, passwords)
        WebViewDatabase.getInstance(context).clearFormData()
        WebViewDatabase.getInstance(context).clearHttpAuthUsernamePassword()

        // Clear Cache and History
        val webView = WebView(context)
        webView.clearCache(true)
        webView.clearHistory()
        webView.clearFormData()
        webView.destroy()
    }

    fun applyIncognitoSettings(settings: android.webkit.WebSettings) {
        settings.apply {
            databaseEnabled = false
            domStorageEnabled = false
            setAppCacheEnabled(false)
            cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
            saveFormData = false
            savePassword = false
        }
    }
}
