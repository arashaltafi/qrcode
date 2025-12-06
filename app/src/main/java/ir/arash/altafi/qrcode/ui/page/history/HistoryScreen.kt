package ir.arash.altafi.qrcode.ui.page.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.ui.component.LoadingIndicatorType
import ir.arash.altafi.qrcode.ui.component.LoadingIndicators
import ir.arash.altafi.qrcode.utils.base.ApiState
import ir.arash.altafi.qrcode.utils.base.BaseScreen
import ir.arash.altafi.qrcode.utils.ext.toast
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val historyViewModelApiState by historyViewModel.apiState.collectAsState()

    BaseScreen(
        onRefresh = {
            historyViewModel.getAll()
        }
    ) {
        when (historyViewModelApiState) {
            is ApiState.Success -> {
//                LazyColumn(
//                    Modifier
//                        .fillMaxSize()
//                        .padding(20.dp)
//                ) {
//                    items(list.size) { count ->
//                        val item = list[count]
//
//                        Column(Modifier.padding(12.dp)) {
//                            Text(
//                                text = item.text,
//                                fontSize = 18.sp
//                            )
//                            Text(
//                                text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
//                                    .format(Date(item.time)),
//                                fontSize = 12.sp,
//                                color = Color.Gray
//                            )
//
//                            HorizontalDivider()
//                        }
//                    }
//                }
            }

            is ApiState.Error -> {
                context.toast((historyViewModelApiState as ApiState.Error).message)
            }

            ApiState.Default -> {}

            else -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicators(
                        isInfinite = true,
                        type = LoadingIndicatorType.CIRCULAR_WAVY
                    )
                }
            }
        }
    }
}