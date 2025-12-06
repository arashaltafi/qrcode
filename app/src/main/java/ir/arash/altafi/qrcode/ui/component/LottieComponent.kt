package ir.arash.altafi.qrcode.ui.component

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieComponent(
    size: DpSize,
    loop: Boolean,
    lottieFile: Int,
    reverseOnRepeat: Boolean,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieFile))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true,
        restartOnPlay = loop,
        reverseOnRepeat = reverseOnRepeat,
        iterations = LottieConstants.IterateForever,
        speed = 1f,
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier.size(size),
        alignment = Alignment.Center
    )
}