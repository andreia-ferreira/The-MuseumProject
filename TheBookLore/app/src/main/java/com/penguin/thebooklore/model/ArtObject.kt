package com.penguin.thebooklore.model

data class ArtObject(
    val id: String,
    val title: String,
    val hasImage: Boolean,
    val imageUrl: String?
)