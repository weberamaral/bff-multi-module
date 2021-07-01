package com.github.weberamaral.samples.dataprovider.apigateway.artist

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Image
import java.net.URI

class FakeArtistGatewayClient() : ArtistGatewayClient {

    override fun getArtist(id: Id): Artist =
        Artist(Id("123"), "AC/DC", 99, Image(100, 100, URI("image.uri")), 99)

    override fun listArtists(severalIds: List<Id>): List<Artist> =
        listOf(
            Artist(Id("123"), "AC/DC", 99, Image(100, 100, URI("image.uri")), 99),
            Artist(Id("456"), "Megadeth", 99, Image(100, 100, URI("image.uri")), 99),
            Artist(Id("789"), "Iron Maiden", 99, Image(100, 100, URI("image.uri")), 99)
        )
}