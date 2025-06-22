package ru.yandexpraktikum.notekeeper.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.yandexpraktikum.add_note.presentation.AddNoteScreen
import ru.yandexpraktikum.add_note.presentation.AddNoteViewModel
import ru.yandexpraktikum.all_notes.presentation.AllNotesScreen
import ru.yandexpraktikum.all_notes.presentation.AllNotesViewModel
import ru.yandexpraktikum.notekeeper.di.ApplicationComponent

@Composable
fun NoteKeeperNavHost(
    appComponent: ApplicationComponent,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AllNotes.route
    ) {
        composable(route = Screen.AllNotes.route) {
            var allNotesComponent by remember { mutableStateOf<Any?>(null) }
            DisposableEffect(Unit) {
                allNotesComponent = appComponent.getAllNotesSubcomponent().create()
                onDispose {
                    allNotesComponent = null
                }
            }
            val vm: AllNotesViewModel = viewModel(
                factory = appComponent.getAllNotesSubcomponent().create()
                    .getAllNotesViewModelFactory()
            )
            AllNotesScreen(
                viewModel = vm,
                onAddNoteClick = {
                    navController.navigate(Screen.AddNote.route)
                }
            )
        }
        composable(route = Screen.AddNote.route) {
            var addNoteContainer by remember { mutableStateOf<Any?>(null) }
            DisposableEffect(Unit) {
                addNoteContainer = appComponent.getAddNoteSubcomponent().create()
                onDispose {
                    addNoteContainer = null
                }
            }
            val vm: AddNoteViewModel = viewModel(
                factory = appComponent.getAddNoteSubcomponent().create()
                    .getAddNoteViewModelFactory()
            )
            AddNoteScreen(
                viewModel = vm,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}