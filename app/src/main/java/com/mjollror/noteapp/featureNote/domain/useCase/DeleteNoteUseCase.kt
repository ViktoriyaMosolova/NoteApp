package com.mjollror.noteapp.featureNote.domain.useCase

import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}