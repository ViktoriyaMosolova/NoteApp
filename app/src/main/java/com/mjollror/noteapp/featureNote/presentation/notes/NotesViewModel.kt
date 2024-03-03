package com.mjollror.noteapp.featureNote.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjollror.noteapp.di.AppModule
import com.mjollror.noteapp.featureNote.domain.model.Note
import com.mjollror.noteapp.featureNote.domain.useCase.NoteUseCases
import com.mjollror.noteapp.featureNote.domain.util.NoteOrder
import com.mjollror.noteapp.featureNote.domain.util.OrderType
import dagger.Component
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    private var recentDeleteNote: Note? = null

    init {
        viewModelScope.launch {
            getAllNotes(NoteOrder.Date(OrderType.Descending))
        }
    }

    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.Order -> {
                if (_state.value.noteOrder::class == event.noteOrder::class &&
                    _state.value.noteOrder.orderType == event.noteOrder.orderType)
                { return }
                getAllNotes(event.noteOrder)
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                    recentDeleteNote = event.note
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(recentDeleteNote ?: return@launch)
                    recentDeleteNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getAllNotes(noteOrder: NoteOrder) {
        viewModelScope.launch {
            getNotesJob?.cancelAndJoin()
            getNotesJob = noteUseCases.getAllNotes(noteOrder)
                .onEach { notes ->
                    _state.value = state.value.copy(
                        notes = notes,
                        noteOrder = noteOrder
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}