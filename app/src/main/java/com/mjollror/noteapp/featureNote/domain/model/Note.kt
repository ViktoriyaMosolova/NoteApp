package com.mjollror.noteapp.featureNote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mjollror.noteapp.ui.theme.*

@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null,
) {
    companion object {
        val noteColors = listOf(
            RedOrange, RedPink, BabyBlue, Violet, LightGreen
        )
    }
}

class InvalidNoteException(message: String): Exception(message)
