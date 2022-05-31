package com.cassani.phrasesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cassani.phrasesapp.data.database.dao.SpellDao
import com.cassani.phrasesapp.data.database.entities.SpellEntity

@Database(entities = [SpellEntity::class], version = 1)
abstract class SpellDataBase : RoomDatabase() {
    abstract fun getSpellDao(): SpellDao
}