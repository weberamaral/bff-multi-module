package com.github.weberamaral.samples.core.usecases.artist.detail

import java.lang.RuntimeException

class NotFoundArtistException(message: String) : RuntimeException(message)