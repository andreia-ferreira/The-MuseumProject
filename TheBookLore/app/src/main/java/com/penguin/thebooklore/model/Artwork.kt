package com.penguin.thebooklore.model

data class Artwork(
    val id: String,
    val title: String,
    val hasImage: Boolean,
    val imageUrl: String?
)