package com.mjollror.noteapp.featureNote.presentation.addEditNotes

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)