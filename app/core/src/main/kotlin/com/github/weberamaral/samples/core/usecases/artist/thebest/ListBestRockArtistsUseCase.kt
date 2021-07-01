package com.github.weberamaral.samples.core.usecases.artist.thebest

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.usecases.UseCase

class ListBestRockArtistsUseCase(private val gatewayRequest: GatewayRequest) : UseCase<List<Id>, List<Artist>> {

    override fun execute(request: List<Id>): List<Artist> {
        if (request.isEmpty() || request.size > 5)
            throw BestRockArtistsException("O número de artistas deve ser maior que ZERO e menor que 5.")
        return gatewayRequest.getBestArtists(request)
    }

    interface GatewayRequest {
        fun getBestArtists(ids: List<Id>) : List<Artist>
    }
}