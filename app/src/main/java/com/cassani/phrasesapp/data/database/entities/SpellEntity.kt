package com.cassani.phrasesapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cassani.phrasesapp.domain.model.Spell

@Entity(tableName = "spell_table")
data class SpellEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "spell") val spell: String,
    @ColumnInfo(name = "use") val use: String
)

fun Spell.toDatabase() = SpellEntity(spell = spell, use= use)