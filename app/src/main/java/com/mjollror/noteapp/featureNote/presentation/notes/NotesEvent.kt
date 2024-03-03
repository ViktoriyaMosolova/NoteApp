package com.mjollror.noteapp.featureNote.presentation.notes

import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder): NotesEvent()
    data class DeleteNote(val note: Note): NotesEvent()
    data object RestoreNote: NotesEvent()
    data object ToggleOrderSection: NotesEvent()
}