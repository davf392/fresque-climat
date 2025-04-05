package com.davidfz.animfresque.ui.animate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.data.AnimationPhase
import com.davidfz.animfresque.data.AnimationPhaseState
import com.davidfz.animfresque.ui.theme.FresqueClimatColors
import androidx.compose.ui.graphics.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@Composable
fun AnimationPhaseItem(
    phaseState: AnimationPhaseState,
    scope: CoroutineScope
) {
    val backgroundColor = if (phaseState.isTimerZero) FresqueClimatColors.Error else Color.Unspecified
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.DragHandle,
                contentDescription = "Déplacer",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = phaseState.phase.name,
                fontSize = 26.sp,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.weight(1f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { phaseState.resetTimer() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Réinitialiser",
                            tint = FresqueClimatColors.Primary,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    if (phaseState.isTimerZero) {
                        Spacer(modifier = Modifier.size(30.dp)) // Add spacer when play/pause is hidden
                    } else {
                        IconButton(onClick = {
                            if (phaseState.isPaused)
                                phaseState.startTimer(scope)
                            else
                                phaseState.pauseTimer()
                        }) {
                            Icon(
                                imageVector = if (phaseState.isPaused) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                                contentDescription = if (phaseState.isPaused) "Start" else "Pause",
                                tint = FresqueClimatColors.Primary,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = if (phaseState.isTimerZero) phaseState.formatElapsedTime() else phaseState.formatDuration(),
                modifier = Modifier.clickable { phaseState.showTimePicker = true },
                fontSize = 30.sp
            )
        }
    }

    if (phaseState.showTimePicker) {
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
            phase = AnimationPhase(
                name = "Intro",
                duration = 15
            ),
            onTimeFinished = {},
            onTimerZero = {}
        ),
        CoroutineScope(Dispatchers.Default)
    )
}