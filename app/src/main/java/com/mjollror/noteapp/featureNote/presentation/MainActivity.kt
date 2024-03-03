package com.mjollror.noteapp.featureNote.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mjollror.noteapp.NoteApp
import com.mjollror.noteapp.featureNote.presentation.addEditNotes.AddEditNoteScreen
import com.mjollror.noteapp.featureNote.presentation.addEditNotes.AddEditNoteViewModel
import com.mjollror.noteapp.featureNote.presentation.notes.NotesScreen
import com.mjollror.noteapp.featureNote.presentation.notes.NotesViewModel
import com.mjollror.noteapp.featureNote.presentation.util.Screen
import com.mjollror.noteapp.ui.theme.NoteAppTheme
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var notesViewModel: NotesViewModel

    @Inject
    lateinit var addEditNoteViewModel: AddEditNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        val appComponent = (application as NoteApp).getAppComponent()
        appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(navController = navController, viewModel = notesViewModel)
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("noteColor") ?: -1
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color,
                                viewModel = addEditNoteViewModel
                            )

                        }
                    }
                }
            }
        }
    }
}