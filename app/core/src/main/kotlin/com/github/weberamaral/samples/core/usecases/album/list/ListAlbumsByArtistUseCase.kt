package com.github.weberamaral.samples.core.usecases.album.list

import com.github.weberamaral.samples.core.entity.Album
import com.github.weberamaral.samples.core.entity.AlbumType
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.usecases.UseCase

class ListAlbumsByArtistUseCase(private val gatewayRequest: GatewayRequest) : UseCase<Id, List<Album>> {

    override fun execute(request: Id): List<Album> = gatewayRequest.getAlbumsByArtistId(request, AlbumType.ALBUM)
        ?: throw NotFoundAlbumByArtistException("Não foi encontrado album do artista ${request.value}")

    interface GatewayRequest {
        fun getAlbumsByArtistId(id: Id, albumType: AlbumType) : List<Album>?
    }
}