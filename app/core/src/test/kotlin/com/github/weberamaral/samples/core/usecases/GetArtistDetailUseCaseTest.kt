package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Image
import com.github.weberamaral.samples.core.usecases.artist.detail.GetArtistDetailUseCase
import com.github.weberamaral.samples.core.usecases.artist.detail.NotFoundArtistException
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.URI

class GetArtistDetailUseCaseTest {
    private val gatewayRequest: GetArtistDetailUseCase.GatewayRequest = Mockito.mock(GetArtistDetailUseCase.GatewayRequest::class.java)
    private val getArtistDetailUseCase = GetArtistDetailUseCase(gatewayRequest)

    @Test fun returnArtistDetail() {
        val expectedArtist = givenArtistIsFound();
        val actualArtist = getArtistDetailUseCase.execute(getId())
        assertThat(actualArtist).isEqualTo(expectedArtist);
    }

    @Test fun errorWhenArtistNotFound() {
        givenArtistNotFound();
        assertThatExceptionOfType(NotFoundArtistException::class.java)
            .isThrownBy { getArtistDetailUseCase.execute(getId())}
    }

    private fun givenArtistIsFound() : Artist {
        val id = Id("2TpxZ7JUBn3uw46aR7qd6V")
        val img = Image(100, 100, URI("https://image.url"))
        val expectedArtist = Artist(id, "AC/DC", 90, img, 30000);
        Mockito.`when`(gatewayRequest.getArtistDetail(getId())).thenReturn(expectedArtist);
        return expectedArtist;
    }

    private fun givenArtistNotFound() {
        Mockito.`when`(gatewayRequest.getArtistDetail(getId())).thenReturn(null)
    }

    private fun getId() : Id = Id("2TpxZ7JUBn3uw46aR7qd6V")
}