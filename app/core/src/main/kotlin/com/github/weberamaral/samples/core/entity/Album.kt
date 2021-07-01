package com.github.weberamaral.samples.core.entity

import java.time.LocalDateTime

data class Album(
    val id: Id,
    val name: String,
    val releaseDate: LocalDateTime,
    val image: Image,
    val albumType: AlbumType
)

enum class AlbumType {
    ALBUM,
    SINGLE,
    COMPILATION
}