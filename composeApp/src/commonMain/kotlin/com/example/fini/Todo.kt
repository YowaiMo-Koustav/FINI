package com.example.fini

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

data class TodoItem(val text: String, var isChecked: Boolean = false)

@Composable
fun TodoList() {
    var text by remember { mutableStateOf("") }
    val items by remember { mutableStateOf(mutableListOf<TodoItem>()) }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // List at the top
        LazyColumn(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            items(items) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    var isChecked by remember { mutableStateOf(item.isChecked) }
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { 
                            isChecked = it
                            item.isChecked = it 
                        }
                    )
                    Text(text = item.text, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }

        // Text field in the center
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            placeholder = { Text("Add a new task") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if (text.isNotBlank()) {
                    items.add(TodoItem(text))
                    text = ""
                }
            })
        )
    }
}
