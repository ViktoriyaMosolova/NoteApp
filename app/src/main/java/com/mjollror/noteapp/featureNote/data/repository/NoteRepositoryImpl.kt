package com.mjollror.noteapp.featureNote.data.repository

import com.mjollror.noteapp.featureNote.data.source.NoteDao
import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val dao: NoteDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> {
        return dao.getAllNotes()
    }

    override suspend fun getNoteById(id: Int): Note? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(note: Note) {
        dao.insertNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note)
    }
}




























