package com.cassani.phrasesapp.data.network

import com.cassani.phrasesapp.data.model.SpellModel
import retrofit2.Response
import retrofit2.http.GET

interface SpellApiClient {

    @GET("hechizos")
    suspend fun getAllSpells(): Response<List<SpellModel>>

}