package com.example.todolistkmp.todo

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TodoController(
    private val repository: TodoRepository
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val input = MutableStateFlow("")


    val state: StateFlow<TodoState> = combine(
        repository.observeTodos(),
        input
    ) { todos, input ->
        TodoState(
            items = todos,
            input = input
        )
    }.stateIn(
        scope,
        SharingStarted.WhileSubscribed(5_000),
        TodoState()
    )

    fun onInputChange(text: String) {
        input.value = text
    }

    fun addTodo() {
        val title = input.value.trim()
        if (title.isBlank()) return
        scope.launch {
            repository.addTodo(title)
            input.value = ""
        }
    }

    fun toggleTodo(id: Long) {
        scope.launch {
            repository.toggleTodo(id)
        }
    }

    fun deleteTodo(id: Long) {
        scope.launch {
            repository.deleteTodo(id)
        }
    }

    fun close() {
        scope.coroutineContext.cancel()
    }

}