package com.base.andres.splashy.domain.usecase

import com.base.andres.splashy.UnitTest
import com.base.andres.splashy.domain.Either
import com.base.andres.splashy.domain.repository.ArtworkRepository
import com.base.andres.splashy.dummy.ArtworkDummyFactory
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class SearchArtworksTest: UnitTest() {
    companion object {
        private const val SOME_KEYWORKS = "medusa by bernini"
        @JvmField val SOME_ARTWORKS = ArtworkDummyFactory.getSomeArtworks()
    }

    private lateinit var searchArtworks: SearchArtworks

    @Mock private lateinit var artworkRepository: ArtworkRepository

    @Before
    fun setUp() {
        searchArtworks = SearchArtworks(artworkRepository)
        given { artworkRepository.search(SOME_KEYWORKS) }.willReturn(Either.Right(SOME_ARTWORKS))
    }

    @Test
    fun `should search artworks in repository`() {
        val params = SearchArtworks.Params(SOME_KEYWORKS)

        runBlocking { searchArtworks.execute(params) }

        verify(artworkRepository).search(eq(SOME_KEYWORKS))
        verifyNoMoreInteractions(artworkRepository)
    }
}