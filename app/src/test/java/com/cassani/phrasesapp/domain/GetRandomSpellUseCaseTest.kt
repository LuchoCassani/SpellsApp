package com.cassani.phrasesapp.domain

import com.cassani.phrasesapp.data.network.SpellRepository
import com.cassani.phrasesapp.domain.model.Spell
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomSpellUseCaseTest{
    @RelaxedMockK
    private lateinit var spellRepository: SpellRepository

    lateinit var getRandomSpellUseCase: GetRandomSpellUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomSpellUseCase = GetRandomSpellUseCase(spellRepository)
    }

    @Test
    fun `when data base is empty return null`() = runBlocking {
        //Given
        coEvery { spellRepository.getAllSpellsFromDataBase() } returns emptyList()
        //When
        val response =  getRandomSpellUseCase()
        //Then
        assert(response == null)
    }

    @Test
    fun `when data base is full return quote`() = runBlocking {
        //Given
        val spellList = listOf(Spell("hechizo","uso"))
        coEvery { spellRepository.getAllSpellsFromDataBase() } returns spellList
        //When
        val response = getRandomSpellUseCase()

        //Then
        assert(spellList.first() == response)

    }
}