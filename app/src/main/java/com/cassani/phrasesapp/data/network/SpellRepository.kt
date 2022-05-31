package com.cassani.phrasesapp.data.network

import com.cassani.phrasesapp.data.database.dao.SpellDao
import com.cassani.phrasesapp.data.database.entities.SpellEntity


import com.cassani.phrasesapp.data.model.SpellModel

import com.cassani.phrasesapp.domain.model.Spell
import com.cassani.phrasesapp.domain.model.toDomain
import javax.inject.Inject

class SpellRepository @Inject constructor(
    private val api: SpellService,
    private val spellDao: SpellDao
) {


    suspend fun getAllSpellsFromApi(): List<Spell> {
        val response: List<SpellModel> = api.getSpells()
        return response.map { it.toDomain() }
    }

    suspend fun getAllSpellsFromDataBase(): List<Spell>{
        val response = spellDao.getAllSpells()
        return response.map { it.toDomain() }
    }

   suspend fun insertSpells(spells: List<SpellEntity>) {
       spellDao.insertAll(spells)

    }

    suspend fun clearSpells() {
        spellDao.deleteAllSpells()
    }
}