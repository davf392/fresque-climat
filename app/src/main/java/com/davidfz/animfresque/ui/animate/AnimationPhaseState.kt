package com.davidfz.animfresque.ui.animate

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

const val TAG = "AnimationPhaseState"

class AnimationPhaseState(val name: String = "", val timer: CountDownTimer) {

    var editedDuration by mutableIntStateOf(timer.initialDuration)
    var showTimePicker by mutableStateOf(false)

    init {
        initDuration(timer.initialDuration)
    }

    private fun initDuration(totalSeconds: Int) {
        setNewDuration(totalSeconds / 60, totalSeconds % 60)
    }

    fun setNewDuration(minutes: Int, seconds: Int) {
        Log.d(TAG, "setNewDuration($minutes, $seconds)")
        editedDuration = (minutes * 60) + seconds
        timer.initialDuration = editedDuration
        showTimePicker = false
        timer.reset()
    }
}