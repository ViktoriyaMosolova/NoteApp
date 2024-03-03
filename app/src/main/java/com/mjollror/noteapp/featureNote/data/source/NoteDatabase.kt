package com.mjollror.noteapp.featureNote.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mjollror.noteapp.featureNote.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object {
        const val DATABASE_NAME = "notes_db"
    }
}