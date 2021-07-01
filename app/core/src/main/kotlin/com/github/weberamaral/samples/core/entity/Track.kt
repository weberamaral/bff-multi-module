package com.github.weberamaral.samples.core.entity

import java.net.URI

data class Track(
    val id: Id,
    val name: String,
    val duration: Int,
    val explicit: Boolean,
    val popularity: Int,
    val trackNumber: Int,
    val previewUrl: URI,
)