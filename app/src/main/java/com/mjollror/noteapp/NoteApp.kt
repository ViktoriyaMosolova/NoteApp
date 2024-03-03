package com.mjollror.noteapp

import android.app.Application
import com.mjollror.noteapp.di.AppComponent
import com.mjollror.noteapp.di.AppModule
import com.mjollror.noteapp.di.DaggerAppComponent

class NoteApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}