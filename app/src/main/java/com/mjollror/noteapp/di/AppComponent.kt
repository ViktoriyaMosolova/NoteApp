package com.mjollror.noteapp.di

import com.mjollror.noteapp.di.AppModule
import com.mjollror.noteapp.featureNote.presentation.MainActivity
import com.mjollror.noteapp.featureNote.presentation.addEditNotes.AddEditNoteViewModel
import com.mjollror.noteapp.featureNote.presentation.notes.NotesViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun notesViewModel(): NotesViewModel
    fun addEditNoteViewModel(): AddEditNoteViewModel
}


