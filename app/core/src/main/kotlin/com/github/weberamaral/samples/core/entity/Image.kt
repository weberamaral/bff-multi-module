package com.github.weberamaral.samples.core.entity

import java.net.URI

data class Image(
    val height: Int,
    val width: Int,
    val url: URI
)