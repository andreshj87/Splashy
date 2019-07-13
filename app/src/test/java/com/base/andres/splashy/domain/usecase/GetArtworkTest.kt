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

class GetArtworkTest: UnitTest() {
    companion object {
        @JvmField val SOME_ARTWORK = ArtworkDummyFactory.getSomeArtwork()
        @JvmField val SOME_ID = SOME_ARTWORK.id
    }

    private lateinit var getArtwork: GetArtwork

    @Mock private lateinit var artworkRepository: ArtworkRepository

    @Before
    fun setUp() {
        getArtwork = GetArtwork(artworkRepository)
        given { artworkRepository.get(SOME_ID) }.willReturn(Either.Right(SOME_ARTWORK))
    }

    @Test
    fun `should get artwork by id from repository`() {
        val params = GetArtwork.Params(SOME_ID)

        runBlocking { getArtwork.execute(params) }

        verify(artworkRepository).get(eq(SOME_ID))
        verifyNoMoreInteractions(artworkRepository)
    }
}