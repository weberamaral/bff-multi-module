package com.github.weberamaral.samples.core.usecases.artist.detail

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.usecases.UseCase

class GetArtistDetailUseCase(private val gatewayRequest: GatewayRequest) : UseCase<Id, Artist> {

    override fun execute(request: Id) = gatewayRequest.getArtistDetail(request)
        ?: throw NotFoundArtistException("Não foi encontrado o artista com id: $request")

    interface GatewayRequest {
        fun getArtistDetail(id: Id) : Artist?
    }
}