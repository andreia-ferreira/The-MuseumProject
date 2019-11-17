package com.penguin.thebooklore.model

import com.penguin.thebooklore.repository.network.model.NetworkWebImage

data class ArtObject(
    val id: String,
    val title: String,
    val hasImage: Boolean,
    val imageUrl: String?
)