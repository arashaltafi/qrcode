package ir.arash.altafi.qrcode.ui.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ContainedLoadingIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.LoadingIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

enum class LoadingIndicatorType {
    LINEAR,
    LINEAR_WAVY,
    CIRCULAR,
    CIRCULAR_WAVY,
    CONTAINED,
    INDICATOR,
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
        LoadingIndicatorType.CONTAINED -> {
            if (isInfinite) {
                ContainedLoadingIndicator()
            } else {
                ContainedLoadingIndicator(
                    progress = { progress }
                )
            }
        }

        LoadingIndicatorType.LINEAR -> {
            if (isInfinite) {
                LinearProgressIndicator()
            } else {
                LinearProgressIndicator(
                    progress = { progress }
                )
            }
        }

        LoadingIndicatorType.LINEAR_WAVY -> {
            if (isInfinite) {
                LinearWavyProgressIndicator()
            } else {
                LinearWavyProgressIndicator(
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

        LoadingIndicatorType.CIRCULAR_WAVY -> {
            if (isInfinite) {
                CircularWavyProgressIndicator()
            } else {
                CircularWavyProgressIndicator(
                    progress = { progress }
                )
            }
        }

        LoadingIndicatorType.INDICATOR -> {
            if (isInfinite) {
                LoadingIndicator()
            } else {
                LoadingIndicator(
                    progress = { progress }
                )
            }
        }
    }
}