package ir.arash.altafi.qrcode.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Blue300,
    secondary = Sky400,
    tertiary = Teal200,
    background = Gray950,
    surface = Gray975,
    onPrimary = Black,
    onSecondary = Black,
    onTertiary = Black,
    onBackground = Gray300,
    onSurface = Gray500,
)

@Composable
fun QrCodeTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}