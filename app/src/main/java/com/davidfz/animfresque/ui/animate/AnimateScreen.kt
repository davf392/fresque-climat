package com.davidfz.animfresque.ui.animate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidfz.animfresque.ui.animate.components.AnimationControlButton
import com.davidfz.animfresque.ui.animate.components.AnimationPhaseItem
import com.davidfz.animfresque.ui.animate.components.AnimationTotalProgressBar
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun AnimateScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimateViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.padding(8.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.animationPhases.size) { index ->
                AnimationPhaseItem(
                    isActive = state.currentlyActiveTimer == index,
                    isAnimationRunning = state.animationPlayState.isPlaying,
                    phaseState = state.animationPhases[index],
                    scope = scope,
                    onDurationChange = { min, sec -> viewModel.setPhaseDuration(index, min, sec) },
                    onShowTimePicker = { show -> viewModel.setShowTimePicker(index, show) }
                )
            }
        }
        AnimationTotalProgressBar(
            modifier = Modifier.padding(top = 10.dp),
            totalAnimationTime = state.totalDurationInSec,
            remainingAnimationTime = state.totalRemainingTimeInSec,
            remainingAnimationFormattedTime = state.totalRemainingTimeFormatted,
            totalTimeFormatted = state.totalTimeFormatted,
            plannedEndTimeFormatted = state.plannedEndTimeFormatted,
            animationIsStarted = state.animationPlayState.isPlaying
        )
        Row {
            AnimationControlButton(
                onClick = { viewModel.startStopAnimation() },
                modifier = Modifier.weight(1f).padding(start = 4.dp, top = 4.dp),
                icon = state.animationPlayState.iconAnimation,
                text = state.animationPlayState.iconButtonText
            )

            if (state.animationPlayState.isPlaying) {
                AnimationControlButton(
                    onClick = { viewModel.startPauseAnimation() },
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp).width(60.dp),
                    icon = state.animationPlayState.iconPlayPause
                )
                AnimationControlButton(
                    onClick = { viewModel.nextAnimationPhase() },
                    modifier = Modifier.padding(start = 4.dp, top = 4.dp).width(60.dp),
                    icon = Icons.Filled.SkipNext
                )
            }
        }
    }
}

// --- Previews ---

@Preview
@Composable
fun AnimateScreenPreview(
    @PreviewParameter(AnimateScreenPreviewProvider::class) uiState: AnimationUiState
) {
    AnimateScreen(
        modifier = Modifier.background(White),
        viewModel = FakeAnimateViewModel(uiState)
    )
}

class FakeAnimateViewModel(uiAnimationState: AnimationUiState): AnimateViewModel() {
    override val uiState = MutableStateFlow(uiAnimationState)
}

class AnimateScreenPreviewProvider : PreviewParameterProvider<AnimationUiState> {
    override val values = sequenceOf(
        AnimationUiState(
            animationPhases = mutableStateListOf(
                AnimationPhaseUiState("Intro", CountDownTimer(15 * 60)),
                AnimationPhaseUiState("Lot 1", CountDownTimer(10 * 60)),
                AnimationPhaseUiState("Lot 2", CountDownTimer(15 * 60)),
                AnimationPhaseUiState("Lot 3", CountDownTimer(20 * 60)),
                AnimationPhaseUiState("Lot 4", CountDownTimer(15 * 60)),
                AnimationPhaseUiState("Lot 5", CountDownTimer(10 * 60)),
                AnimationPhaseUiState("Créativité", CountDownTimer(10 * 60)),
                AnimationPhaseUiState("Synthèse", CountDownTimer(5 * 60)),
                AnimationPhaseUiState("Quiz", CountDownTimer(15 * 60)),
                AnimationPhaseUiState("Émotions", CountDownTimer(15 * 60)),
                AnimationPhaseUiState("Débats", CountDownTimer(45 * 60)),
                AnimationPhaseUiState("Conclusion", CountDownTimer(10 * 60))
            ),
            totalDurationInSec = 10800,
            totalRemainingTimeInSec = 8000,
            totalRemainingTimeFormatted = "02:45:44",
            totalTimeFormatted = "03:05:00",
            animationPlayState = AnimationPlayState.RESUMED,
            currentlyActiveTimer = 0
        )
    )
}