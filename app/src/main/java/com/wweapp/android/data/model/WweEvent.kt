package com.wweapp.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "wwe_events")
data class WweEvent(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val eventName: String,
    val eventDate: String,
    val eventTime: String,
    val eventDateTime: LocalDateTime,
    val eventType: String,
    val eventLocation: String?,
    val eventBrand: String,
    val eventDescription: String?,
    val isMainShow: Boolean = false,
    val meccaTime: String?,
    val streamingLink: String?,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class WweEventUI(
    val id: Long,
    val eventName: String,
    val eventDate: String,
    val eventTime: String,
    val eventType: String,
    val eventLocation: String?,
    val eventBrand: String,
    val eventDescription: String?,
    val isMainShow: Boolean,
    val meccaTime: String?,
    val streamingLink: String?,
    val contentDescriptionForAccessibility: String
)

fun WweEvent.toUI(): WweEventUI {
    val arAccessibility = "حدث WWE: $eventName في $eventDate الساعة $eventTime بتوقيت مكة $meccaTime، النوع: $eventType، العلامة التجارية: $eventBrand"
    return WweEventUI(
        id = id,
        eventName = eventName,
        eventDate = eventDate,
        eventTime = eventTime,
        eventType = eventType,
        eventLocation = eventLocation,
        eventBrand = eventBrand,
        eventDescription = eventDescription,
        isMainShow = isMainShow,
        meccaTime = meccaTime,
        streamingLink = streamingLink,
        contentDescriptionForAccessibility = arAccessibility
    )
}
