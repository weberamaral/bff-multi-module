package com.github.weberamaral.samples.core.entity

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.net.URI

class ImageTest {
    @Test fun testConstructor() {
        val image = Image(100, 100, URI.create("https://image.sample.com"))
        assertEquals(100, image.height)
        assertEquals(100, image.width)
        assertEquals(URI("https://image.sample.com"), image.url)
    }
}