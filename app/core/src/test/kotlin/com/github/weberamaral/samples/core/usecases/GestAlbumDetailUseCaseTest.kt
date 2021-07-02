package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.*
import com.github.weberamaral.samples.core.usecases.album.detail.GetAlbumDetailUseCase
import com.github.weberamaral.samples.core.usecases.album.detail.NotFoundAlbumException
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.net.URI
import java.time.LocalDateTime

class GetAlbumDetailUseCaseTest {
    private val gatewayRequest: GetAlbumDetailUseCase.GatewayRequest = Mockito.mock(GetAlbumDetailUseCase.GatewayRequest::class.java)
    private val getAlbumDetailUseCase = GetAlbumDetailUseCase(gatewayRequest);

    @Test fun returnAlbumDetail() {
        val expectedAlbum = givenAlbumIsFound()
        val actualAlbum = getAlbumDetailUseCase.execute(getIdAlbum())
        assertThat(actualAlbum).isEqualTo(expectedAlbum)
    }

    @Test fun errorWhenNotFoundAlbum() {
        val id = getIdAlbum()
        givenAlbumIsNotFound()
        assertThatExceptionOfType(NotFoundAlbumException::class.java)
            .isThrownBy { getAlbumDetailUseCase.execute(id) }
    }

    private fun givenAlbumIsNotFound() {
        val id = getIdAlbum()
        Mockito.`when`(gatewayRequest.getAlbumDetail(id)).thenReturn(null)
    }

    private fun givenAlbumIsFound(): Album {
        val idAlbum = getIdAlbum()
        val idArtist = getIdArtist()
        val image = Image(100, 100, URI("image.uri"))
        val artist = Artist(idArtist, "AC/DC", 99, image, 50000)
        val album = Album(idAlbum, "Album Name One", LocalDateTime.now(), image, AlbumType.ALBUM, idArtist, artist)

        Mockito.`when`(gatewayRequest.getAlbumDetail(getIdAlbum())).thenReturn(album)
        return album
    }

    private fun getIdArtist() = Id("43977e0YlJeMXG77uCCSMX")
    private fun getIdAlbum() = Id("43977e0YlJeMXG77uCCSMX")
}