package com.wweapp.android.data.model

data class StreamingService(
    val serviceName: String,
    val packageName: String,
    val deepLink: String?,
    val websiteUrl: String?,
    val contentDescription: String
)

val NETFLIX_STREAMING_SERVICE = StreamingService(
    serviceName = "Netflix",
    packageName = "com.netflix.mediaclient",
    deepLink = "nflx://www.netflix.com/title/80018832",
    websiteUrl = "https://www.netflix.com/search?q=WWE",
    contentDescription = "اضغط لفتح WWE على تطبيق Netflix"
)

val PEACOCK_STREAMING_SERVICE = StreamingService(
    serviceName = "Peacock",
    packageName = "com.peacocktv.peacockandroid",
    deepLink = "peacocktv://deeplink/search/WWE",
    websiteUrl = "https://www.peacocktv.com/search?q=WWE",
    contentDescription = "اضغط لفتح WWE على تطبيق Peacock"
)

val YOUTUBE_STREAMING_SERVICE = StreamingService(
    serviceName = "YouTube",
    packageName = "com.google.android.youtube",
    deepLink = "https://www.youtube.com/@WWE",
    websiteUrl = "https://www.youtube.com/@WWE",
    contentDescription = "اضغط لفتح قناة WWE على YouTube"
)

val STREAMING_SERVICES = listOf(
    NETFLIX_STREAMING_SERVICE,
    PEACOCK_STREAMING_SERVICE,
    YOUTUBE_STREAMING_SERVICE
)
