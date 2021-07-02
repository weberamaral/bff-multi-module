package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.*
import com.github.weberamaral.samples.core.usecases.album.list.ListAlbumsByArtistUseCase
import com.github.weberamaral.samples.core.usecases.album.list.NotFoundAlbumByArtistException
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import java.net.URI
import java.time.LocalDateTime

class ListAlbumsByArtistUseCaseTest {
    private val gatewayRequest: ListAlbumsByArtistUseCase.GatewayRequest =
        Mockito.mock(ListAlbumsByArtistUseCase.GatewayRequest::class.java)
    private val listAlbumsByArtistUseCase = ListAlbumsByArtistUseCase(gatewayRequest)

    @Test fun returnAlbumsByArtist() {
        val expectedAlbums = givenValidAlbums()
        val actualAlbums = listAlbumsByArtistUseCase.execute(Id("0LyfQWJT6nXafLPZqxe9Of"))
        assertThat(actualAlbums).isEqualTo(expectedAlbums)
    }

    @Test fun errorWhenNotFoundAlbumsByArtist() {
        givenNotFoundAlbums()
        assertThatExceptionOfType(NotFoundAlbumByArtistException::class.java)
            .isThrownBy { listAlbumsByArtistUseCase.execute(Id("0LyfQWJT6nXafLPZqxe9Of")) }
    }

    private fun givenNotFoundAlbums() {
        Mockito.`when`(gatewayRequest.getAlbumsByArtistId(Id("0LyfQWJT6nXafLPZqxe9Of"), AlbumType.ALBUM)).thenReturn(null)
    }

    private fun givenValidAlbums(): List<Album> {
        val idArtist = Id("0LyfQWJT6nXafLPZqxe9Of")
        val image = Image(100, 100, URI("image.uri"))
        val artist = Artist(idArtist, "AC/DC", 99, image, 50000)
        val albums = listOf(
            Album(Id("43977e0YlJeMXG77uCCSMX"), "Album Name One", LocalDateTime.now(), image, AlbumType.ALBUM, idArtist, artist),
            Album(Id("189ngoT3WxR5mZSYkAGOLF"), "Album Name Two", LocalDateTime.now(), image, AlbumType.ALBUM, idArtist, artist),
            Album(Id("43ZHCT0cAZBISjO8DG9PnE"), "Album Name Three", LocalDateTime.now(), image, AlbumType.ALBUM, idArtist, artist)
        )

        Mockito.`when`(gatewayRequest.getAlbumsByArtistId(Id("0LyfQWJT6nXafLPZqxe9Of"), AlbumType.ALBUM)).thenReturn(albums)
        return albums
    }
}