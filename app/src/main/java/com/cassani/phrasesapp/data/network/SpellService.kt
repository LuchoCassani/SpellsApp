package com.cassani.phrasesapp.data.network

import com.cassani.phrasesapp.data.model.SpellModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SpellService @Inject constructor(
    private val api :SpellApiClient
) {


    suspend fun getSpells(): List<SpellModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllSpells()
            response.body() ?: emptyList()
        }
    }

}