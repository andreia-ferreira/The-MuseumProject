package com.penguin.thebooklore.utils.mapper

import com.penguin.thebooklore.model.Artwork
import com.penguin.thebooklore.model.networkModel.NetworkArtwork

object ArtObjectMapper: IMapper<NetworkArtwork, Artwork> {

    fun mapListArtObject(listResult: List<NetworkArtwork>?) : List<Artwork> {
        return INullableInputListMapperImpl(this).map(listResult)
    }

    override fun map(input: NetworkArtwork): Artwork {
        return Artwork(input.id ?: "Unknown id",
                input.title ?: "Untitled",
                input.hasImage ?: false,
                input.networkWebImage?.url ?: "Unknown url")
    }
}