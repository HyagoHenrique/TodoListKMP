package com.example.todolistkmp.todo

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.todolistkmp.database.TodoDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class TodoRepository(
    private val database: TodoDatabase
) {
    private val queries = database.todoQueries

    fun observeTodos(): Flow<List<TodoItem>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { list ->
                list.map {
                    TodoItem(
                        id = it.id,
                        title = it.title,
                        isDone = it.isDone != 0L,
                        createdAt = it.createdAt
                    )
                }
            }
    }

    @OptIn(ExperimentalTime::class)
    suspend fun addTodo(title: String) {
        val now = Clock.System.now().toEpochMilliseconds()
        queries.insertTodo(title, now)

    }

    suspend fun toggleTodo(id: Long) {
        queries.toggleDone(id)
    }

    suspend fun deleteTodo(id: Long) {
        queries.deleteTodo(id)
    }
}

data class TodoItem(
    val id: Long,
    val title: String,
    val isDone: Boolean,
    val createdAt: Long
)