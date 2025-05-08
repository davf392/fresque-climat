package com.davidfz.animfresque.ui.animate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
        LazyColumn {
            items(state.animationPhases.size) { index ->
                AnimationPhaseItem(state.animationPhases[index], scope)
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
                AnimationPhaseState("Intro", CountDownTimer(15 * 60)),
                AnimationPhaseState("Lot 1", CountDownTimer(10 * 60)),
                AnimationPhaseState("Lot 2", CountDownTimer(15 * 60)),
                AnimationPhaseState("Lot 3", CountDownTimer(20 * 60)),
                AnimationPhaseState("Lot 4", CountDownTimer(15 * 60)),
                AnimationPhaseState("Lot 5", CountDownTimer(10 * 60)),
                AnimationPhaseState("Créativité", CountDownTimer(10 * 60)),
                AnimationPhaseState("Synthèse", CountDownTimer(5 * 60)),
                AnimationPhaseState("Quiz", CountDownTimer(15 * 60)),
                AnimationPhaseState("Émotions", CountDownTimer(15 * 60)),
                AnimationPhaseState("Débats", CountDownTimer(45 * 60)),
                AnimationPhaseState("Conclusion", CountDownTimer(10 * 60))
            ),
            totalTimeInSec = 10800,
            totalTimeFormatted = "03:05:00"
        )
    )
}