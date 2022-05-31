package com.cassani.phrasesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cassani.phrasesapp.data.database.entities.SpellEntity

@Dao
interface SpellDao {

    @Query("SELECT * FROM spell_table ORDER BY spell DESC")
    suspend fun getAllSpells(): List<SpellEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(spells:List<SpellEntity>)

    @Query("DELETE FROM spell_table")
    suspend fun deleteAllSpells()

}