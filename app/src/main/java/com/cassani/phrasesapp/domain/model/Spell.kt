package com.cassani.phrasesapp.domain.model

import com.cassani.phrasesapp.data.database.entities.SpellEntity
import com.cassani.phrasesapp.data.model.SpellModel
import com.google.gson.annotations.SerializedName

data class Spell(
    @SerializedName("hechizo") val spell: String,
    @SerializedName("uso") val use: String
)

fun SpellModel.toDomain()= Spell(spell, use)
fun SpellEntity.toDomain()= Spell(spell, use)