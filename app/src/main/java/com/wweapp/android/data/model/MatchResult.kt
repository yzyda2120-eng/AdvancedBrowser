package com.wweapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_results")
data class MatchResult(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val matchTitle: String,
    val matchersNames: String,
    val winner: String,
    val matchType: String,
    val matchSummary: String,
    val eventName: String,
    val matchDate: String,
    val matchTime: String,
    val highlights: String?,
    val videoUrl: String?,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class MatchResultUI(
    val id: Long,
    val matchTitle: String,
    val matchersNames: String,
    val winner: String,
    val matchType: String,
    val matchSummary: String,
    val eventName: String,
    val matchDate: String,
    val matchTime: String,
    val highlights: String?,
    val videoUrl: String?,
    val contentDescriptionForAccessibility: String
)

fun MatchResult.toUI(): MatchResultUI {
    val arAccessibility = "نتيجة المباراة: $matchTitle، بين $matchersNames، الفائز: $winner، نوع المباراة: $matchType، التلخيص: $matchSummary"
    return MatchResultUI(
        id = id,
        matchTitle = matchTitle,
        matchersNames = matchersNames,
        winner = winner,
        matchType = matchType,
        matchSummary = matchSummary,
        eventName = eventName,
        matchDate = matchDate,
        matchTime = matchTime,
        highlights = highlights,
        videoUrl = videoUrl,
        contentDescriptionForAccessibility = arAccessibility
    )
}
