package com.cassani.phrasesapp.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cassani.phrasesapp.domain.GetSpellsUseCase
import com.cassani.phrasesapp.domain.GetRandomSpellUseCase
import com.cassani.phrasesapp.domain.model.Spell
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SpellViewModelTest {

    @RelaxedMockK
    private lateinit var getSpellsUseCase: GetSpellsUseCase
    @RelaxedMockK
    private lateinit var getRandomSpellUseCase: GetRandomSpellUseCase

    private lateinit var spellViewModel: SpellViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        spellViewModel = SpellViewModel(getSpellsUseCase, getRandomSpellUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }
    @Test
    fun `when viewmodel is created at the first time, get all quotes ands set the first value`() = runTest {
        //Given
        val spellLists = listOf(Spell("hechizo", "uso"), Spell("hechizo","uso"))
        coEvery { getSpellsUseCase() } returns spellLists
        //When
        spellViewModel.onCreate()

        //Then
        assert(spellViewModel.spellModel.value == spellLists.first())
    }
    @Test
    fun `when randomQuoteUseCase returns a quote set on the livedata`() = runTest {
        //Given
        val spell =Spell("hechizo", "autor")
        coEvery { getRandomSpellUseCase() } returns spell

        //When
        spellViewModel.randomSpell()

        //Then
        assert (spellViewModel.spellModel.value == spell)

    }
    @Test
    fun `if randomQuoteUseCase returns null keep the last quote`() = runTest {
        //Given
        val spell =Spell("hechizo", "autor")
        spellViewModel.spellModel.value = spell
        coEvery { getRandomSpellUseCase() } returns null

        //When
        spellViewModel.randomSpell()

        //Then
        assert(spellViewModel.spellModel.value == spell)
    }

}