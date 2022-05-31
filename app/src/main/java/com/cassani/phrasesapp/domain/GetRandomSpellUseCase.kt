package com.cassani.phrasesapp.domain


import com.cassani.phrasesapp.data.network.SpellRepository
import com.cassani.phrasesapp.domain.model.Spell
import javax.inject.Inject


class GetRandomSpellUseCase @Inject constructor(
    private val repository: SpellRepository
) {

   suspend operator fun invoke(): Spell?{
        val spells = repository.getAllSpellsFromDataBase()
        if (!spells.isNullOrEmpty()){
            val randomNumber = (spells.indices).random()
            return spells[randomNumber]
        }
        return null
    }


}