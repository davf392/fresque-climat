package com.davidfz.animfresque.ui.animate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidfz.animfresque.ui.animate.components.AnimationPhaseItem


@Composable
fun AnimateScreen(
    animateViewModel: AnimateViewModel = viewModel()
) {
    val animationPhases by animateViewModel.animationPhases.collectAsState()
    val totalTime by animateViewModel.totalTime.collectAsState()
    val scope = rememberCoroutineScope()

    Column {
        Text(
            text = "Temps total d'animation : ${"%dh%02d".format(totalTime / 60, totalTime % 60)}",
            modifier = Modifier.padding(4.dp),
            fontSize = 24.sp,
        )
        LazyColumn {
            items(animationPhases.size) { index ->
                AnimationPhaseItem(animationPhases[index], scope)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimateScreenPreview() {
    AnimateScreen()
}