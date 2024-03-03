package com.mjollror.noteapp.featureNote.domain.useCase

data class NoteUseCases(
    val getAllNotes: GetAllNotesUseCase,
    val deleteNote: DeleteNoteUseCase,
    val addNote: AddNoteUseCase,
    val getNote: GetNoteUseCase,
)
