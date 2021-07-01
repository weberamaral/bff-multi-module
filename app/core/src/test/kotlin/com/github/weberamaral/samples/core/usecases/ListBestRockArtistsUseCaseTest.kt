package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.Artist
import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Image
import com.github.weberamaral.samples.core.usecases.artist.thebest.BestRockArtistsException
import com.github.weberamaral.samples.core.usecases.artist.thebest.ListBestRockArtistsUseCase
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.URI

class ListBestRockArtistsUseCaseTest {
    private val gatewayRequest: ListBestRockArtistsUseCase.GatewayRequest = Mockito.mock(ListBestRockArtistsUseCase.GatewayRequest::class.java)
    private val listBestRockArtistsUseCase = ListBestRockArtistsUseCase(gatewayRequest);

    @Test fun returnBestArtists() {
        val expectedArtist = givenArtistsIsValid();
        val actualArtist = listBestRockArtistsUseCase.execute(getIds());
        assertThat(actualArtist).isEqualTo(expectedArtist);
    }

    @Test fun errorWhenSizeEquals0() {
        val ids = listOf<Id>();
        givenInvalidArtistsSize(ids);
        assertThatExceptionOfType(BestRockArtistsException::class.java)
            .isThrownBy { listBestRockArtistsUseCase.execute(ids) }
    }

    @Test fun errorWhenSizeGreaterThan5() {
        val ids = listOf(Id("1"), Id("2"), Id("3"), Id("4"), Id("5"), Id("6"))
        givenInvalidArtistsSize(ids);
        assertThatExceptionOfType(BestRockArtistsException::class.java)
            .isThrownBy { listBestRockArtistsUseCase.execute(ids) }
    }

    private fun givenArtistsIsValid() : List<Artist> {
        val expectedArtist = listOf(
            Artist(getIds()[0], "Artist One", 100, Image(100, 100, URI("uri")), 30),
            Artist(getIds()[1], "Artist Two", 100, Image(100, 100, URI("uri")), 30),
            Artist(getIds()[2], "Artist Three", 100, Image(100, 100, URI("uri")), 30),
            Artist(getIds()[3], "Artist Four", 100, Image(100, 100, URI("uri")), 30),
            Artist(getIds()[4], "Artist Five", 100, Image(100, 100, URI("uri")), 30)
        );
        Mockito.`when`(gatewayRequest.getBestRockArtists(getIds())).thenReturn(expectedArtist);
        return expectedArtist;
    }

    private fun givenInvalidArtistsSize(ids: List<Id>): List<Artist> {
        val expectedArtist = listOf<Artist>();
        Mockito.`when`(gatewayRequest.getBestRockArtists(ids)).thenReturn(null);
        return expectedArtist;
    }

    private fun getIds() : List<Id> {
        return listOf(Id("1"), Id("2"), Id("3"), Id("4"), Id("5"))
    }
}