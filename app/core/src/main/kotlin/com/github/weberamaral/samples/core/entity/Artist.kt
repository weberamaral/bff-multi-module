package com.github.weberamaral.samples.core.entity

data class Artist(
    val id: Id,
    val name: String,
    val popularity: Int,
    val image: Image,
    val followers: Int
)