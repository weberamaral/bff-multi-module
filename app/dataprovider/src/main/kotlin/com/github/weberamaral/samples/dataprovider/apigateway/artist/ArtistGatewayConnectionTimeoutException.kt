package com.github.weberamaral.samples.dataprovider.apigateway.artist

import java.lang.RuntimeException

class ArtistGatewayConnectionTimeoutException(message: String) : RuntimeException(message)