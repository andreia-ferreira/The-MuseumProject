package com.penguin.thebooklore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artwork_table")
data class Artwork(
        @PrimaryKey
        val id: String,
        val title: String,
        val hasImage: Boolean,
        val imageUrl: String?,
        var type: String
)