package com.cassani.phrasesapp.domain


import com.cassani.phrasesapp.data.network.SpellRepository
import com.cassani.phrasesapp.domain.model.Spell
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetSpellsUseCaseTest{

    @RelaxedMockK
    private lateinit var spellRepository: SpellRepository

    lateinit var getSpellsUseCase: GetSpellsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getSpellsUseCase = GetSpellsUseCase(spellRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { spellRepository.getAllSpellsFromApi() } returns emptyList()

        //When
        getSpellsUseCase()

        //Then
        coVerify(exactly = 1) { spellRepository.getAllSpellsFromDataBase() }
    }
    @Test
    fun `when the api returns something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Spell("hechizo","uso"))
        coEvery { spellRepository.getAllSpellsFromApi() } returns myList

        //When
        val response = getSpellsUseCase()

        //Then
        coVerify (exactly = 1) { spellRepository.clearSpells() }
        coVerify (exactly = 1){ spellRepository.insertSpells(any()) }
        coVerify(exactly = 0) { spellRepository.getAllSpellsFromDataBase() }
        assert(myList == response)
    }


}