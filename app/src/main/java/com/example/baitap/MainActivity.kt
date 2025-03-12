package com.example.baitap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpandableList()
        }
    }
}

@Composable
fun GreetingScreen(){
    var text by remember { mutableStateOf("quang") }
    var showDialog by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        androidx.compose.material3.Card(
            colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.Gray),
            modifier = Modifier
                .animateContentSize() // Thêm hiệu ứng mềm mại
                .padding(16.dp)
        )
        {
            Text(text = text, modifier = Modifier.padding(16.dp))
            IconButton(onClick = {
                if (text == "quang") {
                    text = "huy"
                } else {
                    text = "quang"
                }
            }, modifier = Modifier.padding(16.dp)) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "hehe"
                )
            }
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
            ) { Text(text = "show dialog") }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "Dialog") },
                    text = { Text(text = "This is a dialog") },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text(text = "Confirm")
                        }
                    })
            }
        }
    }}
@Composable

fun ExpandableCard(title: String, content: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(title, Modifier.weight(1f))
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = null
                    )
                }
            }
            if (expanded) {
                Text(content, Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun ExpandableList() {
    val items = (1..10).map { "Item $it" }
    LazyColumn {
        items(items.size) { index ->
            ExpandableCard(
                title = items[index],
                content = "Lorem ipsum dolor sit amet.\n".repeat(5)
            )
        }
    }
}
