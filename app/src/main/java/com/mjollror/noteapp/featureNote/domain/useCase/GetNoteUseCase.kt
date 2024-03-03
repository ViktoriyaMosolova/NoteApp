package com.mjollror.noteapp.featureNote.domain.useCase

import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.repository.NoteRepository

class GetNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}