package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.UnitTest
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.repository.ArtworkRepository
import com.base.andres.splashy.dummy.ArtworkDummyFactory
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetRecentArtworksTest: UnitTest() {
    companion object {
        @JvmField val SOME_ARTWORKS = ArtworkDummyFactory.getSomeArtworks()
    }

    private lateinit var getRecentArtworks: GetRecentArtworks

    @Mock private lateinit var artworkRepository: ArtworkRepository

    @Before
    fun setUp() {
        getRecentArtworks = GetRecentArtworks(artworkRepository)
        given { artworkRepository.getRecent() }.willReturn(Either.Right(SOME_ARTWORKS))
    }

    @Test
    fun `should get recent artworks from repository`() {
        runBlocking { getRecentArtworks.execute(UseCase.None()) }

        verify(artworkRepository).getRecent()
        verifyNoMoreInteractions(artworkRepository)
    }
}