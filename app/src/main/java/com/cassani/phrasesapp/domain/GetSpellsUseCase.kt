package com.cassani.phrasesapp.domain

import com.cassani.phrasesapp.data.database.entities.toDatabase

import com.cassani.phrasesapp.data.network.SpellRepository
import com.cassani.phrasesapp.domain.model.Spell

import javax.inject.Inject

class GetSpellsUseCase @Inject constructor(private val repository: SpellRepository) {
    suspend operator fun invoke():List<Spell>{
        val spells = repository.getAllSpellsFromApi()

        return if(spells.isNotEmpty()){
            repository.clearSpells()
            repository.insertSpells(spells.map { it.toDatabase() })
            spells
        }else{
            repository.getAllSpellsFromDataBase()
        }
    }
}