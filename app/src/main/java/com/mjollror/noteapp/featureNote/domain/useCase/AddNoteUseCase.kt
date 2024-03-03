package com.mjollror.noteapp.featureNote.domain.useCase

import com.mjollror.noteapp.featureNote.domain.model.InvalidNoteException
import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.repository.NoteRepository

class AddNoteUseCase(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {
            throw InvalidNoteException("The title of the note can't be empty")
        }
        repository.insertNote(note)
    }
}