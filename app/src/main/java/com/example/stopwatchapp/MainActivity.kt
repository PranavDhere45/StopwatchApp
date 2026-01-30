package com.example.stopwatchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                StopwatchScreen()
            }
        }
    }
}

@Composable
fun StopwatchScreen() {
    var timeMillis by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(10L)   // 10 milliseconds
            timeMillis += 10L
        }
    }

    val minutes = (timeMillis / 60000)
    val seconds = (timeMillis / 1000) % 60
    val milliseconds = (timeMillis % 1000) / 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = String.format("%02d:%02d:%02d", minutes, seconds, milliseconds),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { isRunning = true },
                enabled = !isRunning
            ) {
                Text("Start")
            }

            Button(
                onClick = { isRunning = false },
                enabled = isRunning
            ) {
                Text("Pause")
            }

            Button(
                onClick = {
                    isRunning = false
                    timeMillis = 0L
                }
            ) {
                Text("Reset")
            }
        }
    }
}
