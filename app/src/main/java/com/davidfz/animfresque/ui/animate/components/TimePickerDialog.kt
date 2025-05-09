package com.davidfz.animfresque.ui.animate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.concurrent.TimeUnit

@Composable
fun TimePickerDialog(
    onDismissRequest: () -> Unit,
    onTimeChange: (Int, Int) -> Unit,
    initialMinute: Int,
    initialSecond: Int
) {
    var selectedMinute by remember { mutableIntStateOf(initialMinute) }
    var selectedSecond by remember { mutableIntStateOf(initialSecond) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Modifier la durée") },
        text = {
            Row(horizontalArrangement = Arrangement.Center) {
                NumberPicker(
                    modifier = Modifier,
                    value = selectedMinute,
                    onValueChange = { selectedMinute = it },
                    range = 0..59,
                    label = { Text("Minutes", fontSize = 18.sp) }
                )
                Spacer(modifier = Modifier.padding(4.dp))
                NumberPicker(
                    modifier = Modifier,
                    value = selectedSecond,
                    onValueChange = { selectedSecond = it },
                    range = 0..59,
                    label = { Text("Secondes", fontSize = 18.sp) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onTimeChange(selectedMinute, selectedSecond) }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Annuler")
            }
        }
    )
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    var showDialog by remember { mutableStateOf(true) }
    val duration = 15 * 60 * 1000L

    if (showDialog) {
        TimePickerDialog(
            onDismissRequest = { showDialog = false },
            onTimeChange = { minutes, seconds ->
                // Handle duration change in preview
                println("Selected duration: $minutes mn and $seconds sec")
            },
            initialMinute = TimeUnit.MILLISECONDS.toMinutes(duration).toInt(), // Example initial duration
            initialSecond = (TimeUnit.MILLISECONDS.toSeconds(duration) % 60).toInt() // Example initial duration
        )
    }
}