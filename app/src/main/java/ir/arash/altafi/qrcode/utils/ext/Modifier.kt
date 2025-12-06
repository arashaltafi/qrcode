package ir.arash.altafi.qrcode.utils.ext

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.borderBottom(
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
    strokeWidth: Dp = 1.dp
): Modifier = this.drawBehind {
    val strokePx = strokeWidth.toPx()
    drawLine(
        color = color,
        start = Offset(x = 0f, y = size.height - strokePx / 2),
        end = Offset(x = size.width, y = size.height - strokePx / 2),
        strokeWidth = strokePx
    )
}

@Composable
fun Modifier.borderTop(
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
    strokeWidth: Dp = 1.dp
): Modifier = this.drawBehind {
    val strokePx = strokeWidth.toPx()
    drawLine(
        color = color,
        start = Offset(x = 0f, y = strokePx / 2),
        end = Offset(x = size.width, y = strokePx / 2),
        strokeWidth = strokePx
    )
}

@Composable
fun Modifier.borderLeft(
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
    strokeWidth: Dp = 1.dp
): Modifier = this.drawBehind {
    val strokePx = strokeWidth.toPx()
    drawLine(
        color = color,
        start = Offset(x = strokePx / 2, y = 0f),
        end = Offset(x = strokePx / 2, y = size.height),
        strokeWidth = strokePx
    )
}

@Composable
fun Modifier.borderRight(
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
    strokeWidth: Dp = 1.dp
): Modifier = this.drawBehind {
    val strokePx = strokeWidth.toPx()
    drawLine(
        color = color,
        start = Offset(x = size.width - strokePx / 2, y = 0f),
        end = Offset(x = size.width - strokePx / 2, y = size.height),
        strokeWidth = strokePx
    )
}