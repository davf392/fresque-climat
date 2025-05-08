package com.davidfz.animfresque.ui.animate.components

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
import com.davidfz.animfresque.ui.animate.AnimationPhaseState
import com.davidfz.animfresque.ui.animate.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@Composable
fun AnimationPhaseItem(
    phaseState: AnimationPhaseState,
    scope: CoroutineScope
) {
    val isTimerZero by phaseState.timer.isTimerZero.collectAsState()
    val isTimerRunning by phaseState.timer.isRunning.collectAsState()
    val elapsedTimeFormatted by phaseState.timer.elapsedTimeFormatted.collectAsState()
    val remainingTimeFormatted by phaseState.timer.remainingTimeFormatted.collectAsState()

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isTimerZero) FresqueClimatColors.Error else Color.Unspecified
        )
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = phaseState.name,
                fontSize = 26.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                    if (isTimerZero) {
                        Spacer(modifier = Modifier.size(30.dp)) // Add spacer when play/pause is hidden
                    } else {
                        IconButton(onClick = {
                            if (isTimerRunning)
                                phaseState.timer.pause()
                            else
                                phaseState.timer.start(scope)
                        }) {
                            Icon(
                                imageVector = if (isTimerRunning) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                                contentDescription = if (isTimerRunning) "Pause" else "Start",
                                tint = FresqueClimatColors.Primary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (isTimerZero) elapsedTimeFormatted else remainingTimeFormatted,
                modifier = Modifier.clickable { phaseState.showTimePicker = true },
                fontSize = 30.sp
            )
        }
    }

    // time picker should not be clickable if timer is still running
    if (phaseState.showTimePicker && !isTimerRunning) {
        TimePickerDialog(
            onDismissRequest = { phaseState.showTimePicker = false },
            onTimeChange = { minutes, seconds ->
                phaseState.setNewDuration(minutes, seconds)
            },
            initialMinute = phaseState.editedDuration / 60,
            initialSecond = phaseState.editedDuration % 60
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AnimationPhaseItemPreview() {
    AnimationPhaseItem(
        AnimationPhaseState(
            name = "Intro",
            timer = CountDownTimer(initialDuration = 15)
        ),
        CoroutineScope(Dispatchers.Default)
    )
}