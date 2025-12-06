package ir.arash.altafi.qrcode.utils.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun BaseScreen(
    onRefresh: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    var isRefreshing by remember { mutableStateOf(false) }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    if (onRefresh != null) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                scope.launch {
                    val startTime = System.currentTimeMillis()

                    // Trigger actual refresh
                    onRefresh.invoke()

                    // Ensure at least 1 seconds loading
                    val elapsed = System.currentTimeMillis() - startTime
                    val remaining = 1000 - elapsed
                    if (remaining > 0) delay(remaining)
                }
            }
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}