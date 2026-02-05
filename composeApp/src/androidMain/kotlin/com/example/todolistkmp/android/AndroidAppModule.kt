package com.example.todolistkmp.android

import android.content.Context
import com.example.todolistkmp.AppModule
import com.example.todolistkmp.database.DatabaseDriverFactory

class AndroidAppModule(context: Context) {
    val appModule = AppModule(DatabaseDriverFactory(context))
}