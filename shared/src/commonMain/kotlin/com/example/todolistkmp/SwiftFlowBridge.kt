package com.example.todolistkmp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SwiftFlowSubscription(
    private val job: Job
) {
    fun cancel() {
        job.cancel()
    }
}

fun <T> watchFlow(
    flow: Flow<T>,
    onEach: (T) -> Unit
): SwiftFlowSubscription {
    val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val job = scope.launch {
        flow.collect { value ->
            onEach(value)
        }
    }

    return SwiftFlowSubscription(job)

}