package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Track
import com.github.weberamaral.samples.core.usecases.track.list.ListTracksByAlbumUseCase
import com.github.weberamaral.samples.core.usecases.track.list.NotFoundTracksByAlbumException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.net.URI

class ListTracksByAlbumUseCaseTest {
    private val gatewayRequest: ListTracksByAlbumUseCase.GatewayRequest = Mockito.mock(ListTracksByAlbumUseCase.GatewayRequest::class.java)
    private val listTracksByAlbumUseCase = ListTracksByAlbumUseCase(gatewayRequest);

    @Test fun returnTracksByAlbum() {
        val expectedTracks = givenValidTrack()
        val actualTracks = listTracksByAlbumUseCase.execute(getAlbumId())
        assertThat(actualTracks).isEqualTo(expectedTracks);
    }

    @Test fun errorWhenNotFoundTracksByAlbum() {
        givenNotFoundTrack()
        assertThatExceptionOfType(NotFoundTracksByAlbumException::class.java)
            .isThrownBy { listTracksByAlbumUseCase.execute(getAlbumId()) }
    }

    private fun givenNotFoundTrack() {
        Mockito.`when`(gatewayRequest.getTracksByAlbumId(getTrackId())).thenReturn(null)
    }

    private fun getAlbumId() = Id("08td7MxkoHQkXnWAYD8d6Q")
    private fun getTrackId() = Id("08td7MxkoHQkXnWAYD8d6Q")
    private fun getArtistId() = Id("08td7MxkoHQkXnWAYD8d6Q")

    private fun givenValidTrack(): List<Track> {
        val tracks = listOf(
            Track(getTrackId(), "Track One", 120, true, 99, 1, URI("album.uri"), getAlbumId(), null, getArtistId(), null),
            Track(getTrackId(), "Track One", 120, true, 99, 1, URI("album.uri"), getAlbumId(), null, getArtistId(), null),
            Track(getTrackId(), "Track One", 120, true, 99, 1, URI("album.uri"), getAlbumId(), null, getArtistId(), null)
        )

        Mockito.`when`(gatewayRequest.getTracksByAlbumId(getTrackId())).thenReturn(tracks)
        return tracks
    }
}