package com.davidfz.animfresque.ui.animate

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import com.davidfz.animfresque.ui.animate.components.AnimationPhaseItem
import com.davidfz.animfresque.ui.animate.components.AnimationTotalProgressBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun AnimateScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimateViewModel = viewModel(),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier.padding(8.dp),
    ) {
        AnimationTotalProgressBar(
            totalAnimationTime = state.totalTimeInSec,
            remainingAnimationTime = state.totalTimeInSec,
            remainingAnimationFormattedTime = state.totalTimeFormatted
        )
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        LazyColumn {
            items(state.animationPhases.size) { index ->
                AnimationPhaseItem(
                    phaseState = state.animationPhases[index],
                    scope = scope,
                    onDurationChange = { minutes, seconds ->
                        Log.d("AnimateScreen", "setPhaseDuration")
                        viewModel.setPhaseDuration(index, minutes, seconds)
                   },
                    onShowTimePicker = { show ->
                        Log.d("AnimateScreen", "setShowTimePicker")
                        viewModel.setShowTimePicker(index, show)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AnimateScreenPreview(
    @PreviewParameter(AnimateScreenPreviewProvider::class) uiState: AnimationUiState
) {
    AnimateScreen(
        modifier = Modifier.background(White),
        FakeAnimateViewModel(uiState)
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
            totalTimeInSec = 10800,
            totalTimeFormatted = "03:05:00"
        )
    )
}