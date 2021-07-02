package com.github.weberamaral.samples.core.usecases.track.list

import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Track
import com.github.weberamaral.samples.core.usecases.UseCase

class ListTracksByAlbumUseCase(private val gatewayRequest: GatewayRequest) : UseCase<Id, List<Track>> {

    override fun execute(request: Id) = gatewayRequest.getTracksByAlbumId(request)
        ?: throw NotFoundTracksByAlbumException("Não foi encontrado musicals do album ${request.value}");

    interface GatewayRequest {
        fun getTracksByAlbumId(id: Id) : List<Track>?
    }
}