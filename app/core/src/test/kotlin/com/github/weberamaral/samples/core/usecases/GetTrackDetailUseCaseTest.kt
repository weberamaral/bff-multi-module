package com.github.weberamaral.samples.core.usecases

import com.github.weberamaral.samples.core.entity.Id
import com.github.weberamaral.samples.core.entity.Track
import com.github.weberamaral.samples.core.usecases.track.detail.GetTrackDetailUseCase
import com.github.weberamaral.samples.core.usecases.track.detail.NotFoundTrackException
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.assertj.core.api.Assertions.*
import java.net.URI

class GetTrackDetailUseCaseTest {
    private val gatewayRequest: GetTrackDetailUseCase.GatewayRequest = Mockito.mock(GetTrackDetailUseCase.GatewayRequest::class.java)
    private val getTrackDetailUseCase = GetTrackDetailUseCase(gatewayRequest)

    @Test fun returnTrackDetail() {
        val expectedTrack = givenTrackIsFound()
        val actualTrack = getTrackDetailUseCase.execute(getTrackId())
        assertThat(actualTrack).isEqualTo(expectedTrack)
    }

    @Test fun errorWhenTrackIsNotFound() {
        givenTrackIsNotFound()
        assertThatExceptionOfType(NotFoundTrackException::class.java)
            .isThrownBy { getTrackDetailUseCase.execute(getTrackId()) }
    }

    private fun givenTrackIsNotFound() {
        Mockito.`when`(gatewayRequest.getTrackDetail(getTrackId())).thenReturn(null)
    }

    private fun getTrackId() = Id("0LyfQWJT6nXafLPZqxe9Of")
    private fun getAlbumId() = Id("43977e0YlJeMXG77uCCSMX")
    private fun getArtistId() = Id("43ZHCT0cAZBISjO8DG9PnE")

    private fun givenTrackIsFound(): Track {
        val track = Track(getTrackId(), "Music One", 1200, true, 90, 1,
            URI("uri.track"), getAlbumId(), null, getArtistId(), null)
        Mockito.`when`(gatewayRequest.getTrackDetail(getTrackId())).thenReturn(track)
        return track
    }
}