package com.cassani.phrasesapp.di

import android.content.Context
import androidx.room.Room
import com.cassani.phrasesapp.data.database.SpellDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val SPELL_DATABASE_NAME = "spell_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, SpellDataBase::class.java, SPELL_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideSpellDao(db:SpellDataBase) = db.getSpellDao()

}