package com.example.todolistkmp.todo

data class TodoState(
    val items: List<TodoItem> = emptyList(),
    val input: String = ""
)