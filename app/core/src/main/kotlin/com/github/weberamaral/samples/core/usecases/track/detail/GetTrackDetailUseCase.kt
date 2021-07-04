package com.github.weberamaral.samples.core.usecases.track.detail

import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Track
import com.github.weberamaral.samples.core.usecases.UseCase

class GetTrackDetailUseCase(private val gatewayRequest: GatewayRequest): UseCase<Id, Track> {

    override fun execute(request: Id) = gatewayRequest.getTrackDetail(request)
        ?: throw NotFoundTrackException("Não foi possivel encontrar a música com id: ${request.value}")

    interface GatewayRequest {
        fun getTrackDetail(id: Id): Track?
    }
}