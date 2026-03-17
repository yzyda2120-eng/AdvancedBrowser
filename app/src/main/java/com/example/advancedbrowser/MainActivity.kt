package com.example.advancedbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    private lateinit var adBlocker: AdBlocker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adBlocker = AdBlocker(this)

        setContent {
            BrowserScreen(adBlocker)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Incognito feature: Clear data on exit
        clearBrowserData()
    }

    private fun clearBrowserData() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
        WebView(this).clearCache(true)
        WebView(this).clearHistory()
        WebView(this).clearFormData()
    }
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowserScreen(adBlocker: AdBlocker) {
    var url by remember { mutableStateOf("https://www.google.com") }
    var webView: WebView? by remember { mutableStateOf(null) }
    var textFieldValue by remember { mutableStateOf(url) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                            var targetUrl = textFieldValue
                            if (!targetUrl.startsWith("http")) {
                                targetUrl = "https://www.google.com/search?q=$targetUrl"
                            }
                            url = targetUrl
                            webView?.loadUrl(url)
                        }),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                    )
                },
                actions = {
                    IconButton(onClick = { webView?.reload() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Reload")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = BrowserWebViewClient(adBlocker)
                        settings.apply {
                            javaScriptEnabled = true
                            domStorageEnabled = true
                            databaseEnabled = true
                            cacheMode = WebSettings.LOAD_NO_CACHE
                            mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                        }
                        
                        // Incognito: Disable cookies and history
                        CookieManager.getInstance().setAcceptCookie(false)
                        
                        loadUrl(url)
                        webView = this
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
