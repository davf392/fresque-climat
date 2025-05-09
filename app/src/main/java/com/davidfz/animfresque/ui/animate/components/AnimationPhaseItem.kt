package com.davidfz.animfresque.ui.animate.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.ui.theme.FresqueClimatColors
import androidx.compose.ui.graphics.*
import com.davidfz.animfresque.ui.animate.AnimationPhaseUiState
import com.davidfz.animfresque.ui.animate.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@Composable
fun AnimationPhaseItem(
    phaseState: AnimationPhaseUiState,
    scope: CoroutineScope,
    onDurationChange: (Int, Int) -> Unit = { _, _ -> },
    onShowTimePicker: (Boolean) -> Unit = {}
) {
    val isTimerZero by phaseState.timer.isTimerZero.collectAsState()
    val isTimerRunning by phaseState.timer.isRunning.collectAsState()
    val elapsedTimeFormatted by phaseState.timer.elapsedTimeFormatted.collectAsState()
    val remainingTimeFormatted by phaseState.timer.remainingTimeFormatted.collectAsState()
    val backgroundColor = if (isTimerZero) FresqueClimatColors.Error else Color.Unspecified

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // timer name
            Text(
                text = phaseState.name,
                fontSize = 26.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            // timer reset button
            if (phaseState.timer.isStarted()) {
                IconButton(onClick = {
                    phaseState.timer.reset()
                    phaseState.showTimePicker = false
                }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Réinitialiser",
                        tint = FresqueClimatColors.Primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            // timer play or pause button
            if (isTimerZero) {
                Spacer(modifier = Modifier.size(30.dp)) // Add spacer when play/pause is hidden
            } else {
                IconButton(
                    onClick = { phaseState.timer.let { if (isTimerRunning) it.pause() else it.start(scope) } }
                ) {
                    Icon(
                        imageVector = if (isTimerRunning) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                        contentDescription = if (isTimerRunning) "Pause" else "Start",
                        tint = FresqueClimatColors.Primary,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            // timer displayed remaining or elapsed time
            Text(
                text = if (isTimerZero) elapsedTimeFormatted else remainingTimeFormatted,
                modifier = Modifier.clickable { onShowTimePicker(true) },
                fontSize = 30.sp
            )
        }
    }

    // time picker should not be clickable if timer is still running
    if (phaseState.showTimePicker && !isTimerRunning) {
        TimePickerDialog(
            onDismissRequest = { onShowTimePicker(false) },
            onTimeChange = onDurationChange,
            initialMinute = phaseState.editedDuration / 60,
            initialSecond = phaseState.editedDuration % 60
        )
    }
}


@Preview
@Composable
fun AnimationPhaseItemPreview() {
    AnimationPhaseItem(
        AnimationPhaseUiState(
            name = "Intro",
            timer = CountDownTimer(initialDuration = 15)
        ),
        CoroutineScope(Dispatchers.Default),
    )
}