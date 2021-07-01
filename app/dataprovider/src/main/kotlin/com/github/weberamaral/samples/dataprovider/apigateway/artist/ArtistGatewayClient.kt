package com.github.weberamaral.samples.dataprovider.apigateway.artist

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id

interface ArtistGatewayClient {
    fun getArtist(id: Id) : Artist
    fun listArtists(severalIds: List<Id>) : List<Artist>
}