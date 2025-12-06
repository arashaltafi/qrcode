package ir.arash.altafi.qrcode.ui.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

enum class LoadingIndicatorType {
    LINEAR,
    CIRCULAR
}

@Composable
fun LoadingIndicators(
    isInfinite: Boolean,
    type: LoadingIndicatorType
) {
    // Progress indicators
    val transition = rememberInfiniteTransition()
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000),
        )
    )

    when (type) {
        LoadingIndicatorType.LINEAR -> {
            if (isInfinite) {
                LinearProgressIndicator()
            } else {
                LinearProgressIndicator(
                    progress = { progress }
                )
            }
        }

        LoadingIndicatorType.CIRCULAR -> {
            if (isInfinite) {
                CircularProgressIndicator()
            } else {
                CircularProgressIndicator(
                    progress = { progress }
                )
            }
        }
    }
}