package com.davidfz.animfresque.ui.animate.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    label: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        label()
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onValueChange((value - 1).coerceIn(range)) }) {
                Icon(Icons.Outlined.RemoveCircleOutline, contentDescription = "Decrease")
            }
            Text(text = value.toString().padStart(2, '0'))
            IconButton(onClick = { onValueChange((value + 1).coerceIn(range)) }) {
                Icon(Icons.Outlined.AddCircleOutline, contentDescription = "Increase")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberPickerMinutesPreview() {
    var value by remember { mutableIntStateOf(15) }
    NumberPicker(
        value = value,
        onValueChange = { value = it },
        range = 0..59,
        label = { Text("Minutes") }
    )
}

@Preview(showBackground = true)
@Composable
fun NumberPickerSecondsPreview() {
    var value by remember { mutableIntStateOf(0) }
    NumberPicker(
        value = value,
        onValueChange = { value = it },
        range = 0..59,
        label = { Text("Secondes") }
    )
}