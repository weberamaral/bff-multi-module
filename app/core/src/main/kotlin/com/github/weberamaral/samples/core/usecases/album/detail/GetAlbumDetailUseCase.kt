package com.github.weberamaral.samples.core.usecases.album.detail

import com.github.weberamaral.samples.core.entity.Album
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.usecases.UseCase

class GetAlbumDetailUseCase(private val gatewayRequest: GatewayRequest) : UseCase<Id, Album> {

    override fun execute(request: Id) = gatewayRequest.getAlbumDetail(request)
        ?: throw NotFoundAlbumException("Não foi encontrado album com id: ${request.value}")

    interface GatewayRequest {
        fun getAlbumDetail(id: Id) : Album?
    }
}