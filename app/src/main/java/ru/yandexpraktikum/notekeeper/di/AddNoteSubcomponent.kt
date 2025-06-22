package ru.yandexpraktikum.notekeeper.di

import dagger.Subcomponent
import ru.yandexpraktikum.add_note.di.AddNoteModule
import ru.yandexpraktikum.add_note.presentation.AddNoteViewModelFactory
import ru.yandexpraktikum.notekeeper.di.scopes.AddNoteScope

@AddNoteScope
@Subcomponent(modules = [AddNoteModule::class])
interface AddNoteSubcomponent {
    fun getAddNoteViewModelFactory():AddNoteViewModelFactory

    @Subcomponent.Factory
    interface Factory{
        fun create():AddNoteSubcomponent
    }
}