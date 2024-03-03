package com.mjollror.noteapp.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mjollror.noteapp.featureNote.data.repository.NoteRepositoryImpl
import com.mjollror.noteapp.featureNote.data.source.NoteDatabase
import com.mjollror.noteapp.featureNote.domain.repository.NoteRepository
import com.mjollror.noteapp.featureNote.domain.useCase.AddNoteUseCase
import com.mjollror.noteapp.featureNote.domain.useCase.DeleteNoteUseCase
import com.mjollror.noteapp.featureNote.domain.useCase.GetAllNotesUseCase
import com.mjollror.noteapp.featureNote.domain.useCase.GetNoteUseCase
import com.mjollror.noteapp.featureNote.domain.useCase.NoteUseCases
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
    private val context: Context
) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getAllNotes = GetAllNotesUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository),
            addNote = AddNoteUseCase(repository),
            getNote = GetNoteUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }
}