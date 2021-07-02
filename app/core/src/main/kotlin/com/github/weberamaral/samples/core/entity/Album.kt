package com.github.weberamaral.samples.core.entity

import java.time.LocalDateTime

data class Album(
    val id: Id,
    val name: String,
    val releaseDate: LocalDateTime,
    val image: Image,
    val albumType: AlbumType,

    val artistId: Id,
    val artist: Artist?
)

enum class AlbumType {
    ALBUM,
    SINGLE,
    COMPILATION
}