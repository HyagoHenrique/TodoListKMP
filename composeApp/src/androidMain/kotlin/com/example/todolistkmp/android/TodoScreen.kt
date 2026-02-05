package com.example.todolistkmp.android

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todolistkmp.todo.TodoController
import com.example.todolistkmp.todo.TodoItem

@Composable
fun TodoScreen(controller: TodoController) {
    val state by controller.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Lista de Tarefas",
            style = MaterialTheme.typography.headlineMedium
            )

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = state.input,
                onValueChange = {controller.onInputChange(it)},
                modifier = Modifier.weight(1f),
                placeholder = { Text("Digite uma tarefa...") },
                singleLine = true
            )

            Spacer(Modifier.width(12.dp))

            Button(onClick = { controller.addTodo() }) {
                Text("Adicionar")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = state.items,
                key = { it.id }
            ) { item ->
                TodoRow(
                    item = item,
                    onToggle = { controller.toggleTodo(item.id) },
                    onDelete = { controller.deleteTodo(item.id) }
                )
            }
        }
    }
}

@Composable
private fun TodoRow(
    item: TodoItem,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = item.isDone,
                onCheckedChange = { onToggle() }
            )

            Spacer(Modifier.width(10.dp))

            Text(
                text = item.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (item.isDone) TextDecoration.LineThrough else null
            )

            Spacer(Modifier.width(10.dp))

            TextButton(onClick = onDelete) {
                Text("Delete")
            }
        }
    }
}