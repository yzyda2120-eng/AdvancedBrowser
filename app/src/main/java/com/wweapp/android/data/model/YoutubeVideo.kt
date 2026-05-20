package com.wweapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "youtube_videos")
data class YoutubeVideo(
    @PrimaryKey
    val videoId: String,
    val videoTitle: String,
    val videoDescription: String,
    val thumbnailUrl: String,
    val publishedAt: String,
    val channelTitle: String,
    val viewCount: String?,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class YoutubeVideoUI(
    val videoId: String,
    val videoTitle: String,
    val videoDescription: String,
    val thumbnailUrl: String,
    val publishedAt: String,
    val channelTitle: String,
    val viewCount: String?,
    val contentDescriptionForAccessibility: String
)

fun YoutubeVideo.toUI(): YoutubeVideoUI {
    val arAccessibility = "فيديو يوتيوب: $videoTitle، نشر بواسطة $channelTitle في $publishedAt، عدد المشاهدات: $viewCount"
    return YoutubeVideoUI(
        videoId = videoId,
        videoTitle = videoTitle,
        videoDescription = videoDescription,
        thumbnailUrl = thumbnailUrl,
        publishedAt = publishedAt,
        channelTitle = channelTitle,
        viewCount = viewCount,
        contentDescriptionForAccessibility = arAccessibility
    )
}
