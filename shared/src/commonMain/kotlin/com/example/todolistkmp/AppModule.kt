package com.example.todolistkmp

import com.example.todolistkmp.database.DatabaseDriverFactory
import com.example.todolistkmp.database.TodoDatabase
import com.example.todolistkmp.todo.TodoController
import com.example.todolistkmp.todo.TodoRepository

class AppModule(
    driverFactory: DatabaseDriverFactory
) {
    private val database = TodoDatabase(driverFactory.createDriver())

    val todoRepository: TodoRepository = TodoRepository(database)

    val todoController: TodoController = TodoController(todoRepository)
}