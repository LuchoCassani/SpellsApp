package com.cassani.phrasesapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cassani.phrasesapp.domain.GetSpellsUseCase
import com.cassani.phrasesapp.domain.GetRandomSpellUseCase
import com.cassani.phrasesapp.domain.model.Spell
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpellViewModel @Inject constructor(
    private val getSpellsUseCase: GetSpellsUseCase,
    private val getRandomSpellUseCase: GetRandomSpellUseCase
) : ViewModel() {

    val spellModel = MutableLiveData<Spell>()
    val isLoading = MutableLiveData<Boolean>()


    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getSpellsUseCase()

            if (!result.isNullOrEmpty()) {
                spellModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomSpell() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val spell = getRandomSpellUseCase()
            if (spell != null) {
                spellModel.postValue(spell)
            }
            isLoading.postValue(false)
        }
    }


}