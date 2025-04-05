package com.davidfz.animfresque.ui.animate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidfz.animfresque.data.AnimationPhase
import com.davidfz.animfresque.data.AnimationPhaseState


@Composable
fun AnimateScreen() {
    val scope = rememberCoroutineScope()
    val phases = remember {
        mutableStateListOf(
            AnimationPhase("Intro", 15 * 60),
            AnimationPhase("Lot 1", 10 * 60),
            AnimationPhase("Lot 2", 15 * 60),
            AnimationPhase("Lot 3", 20 * 60),
            AnimationPhase("Lot 4", 15 * 60),
            AnimationPhase("Lot 5", 10 * 60),
            AnimationPhase("Créativité", 10 * 60),
            AnimationPhase("Synthèse", 5 * 60),
            AnimationPhase("Quiz", 15 * 60),
            AnimationPhase("Émotions", 15 * 60),
            AnimationPhase("Débats", 45 * 60),
            AnimationPhase("Conclusion", 10 * 60)
        ).map { phase ->
            AnimationPhaseState(phase, onTimeFinished = {
                // Time finished action, can be empty
            }, onTimerZero = {}) // Add onTimerZero
        }
    }

    val totalTime by remember {
        derivedStateOf {
            phases.sumOf { it.editedDuration } / 60
        }
    }
    Column {
        Text(
            text = "Temps total d'animation : ${"%dh%02d".format(totalTime / 60, totalTime % 60)}",
            modifier = Modifier.padding(4.dp),
            fontSize = 24.sp,
        )
        LazyColumn {
            items(phases.size) { index ->
                AnimationPhaseItem(phases[index], scope)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimateScreenPreview() {
    AnimateScreen()
}