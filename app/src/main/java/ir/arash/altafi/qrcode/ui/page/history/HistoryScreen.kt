package ir.arash.altafi.qrcode.ui.page.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.arash.altafi.qrcode.navigation.Route
import ir.arash.altafi.qrcode.ui.component.EmptyLayout
import ir.arash.altafi.qrcode.ui.component.LoadingIndicatorType
import ir.arash.altafi.qrcode.ui.component.LoadingIndicators
import ir.arash.altafi.qrcode.utils.Utils
import ir.arash.altafi.qrcode.utils.base.ApiState
import ir.arash.altafi.qrcode.utils.base.BaseScreen
import ir.arash.altafi.qrcode.utils.ext.toast

@Composable
fun HistoryScreen(
    historyViewModel: HistoryViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current
    val historyViewModelApiState by historyViewModel.apiState.collectAsState()

    LaunchedEffect(Unit) {
        historyViewModel.getAll()
    }

    BaseScreen(
        onRefresh = {
            historyViewModel.getAll()
        }
    ) {
        when (historyViewModelApiState) {
            is ApiState.Success -> {
                val list = (historyViewModelApiState as ApiState.Success).data

                if (list.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        EmptyLayout()
                    }
                } else {
                    LazyColumn(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        items(list.size) { count ->
                            val item = list[count]

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp),
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 4.dp
                                ),
                                shape = RoundedCornerShape(12.dp),
                                onClick = {
                                    navController.navigate(
                                        Route.Detail(
                                            id = item.id
                                        )
                                    )
                                }
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp, horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        bitmap = item.bitmap.asImageBitmap(),
                                        contentDescription = "QR Image",
                                        modifier = Modifier
                                            .size(70.dp)
                                            .padding(end = 12.dp)
                                    )

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f),
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            text = item.text,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = Utils.formatReminderDate(item.time),
                                            fontSize = 14.sp,
                                            color = Color.Gray,
                                        )
                                    }

                                    Icon(
                                        imageVector = Icons.Default.RemoveRedEye,
                                        contentDescription = "Delete",
                                    )
                                }
                            }
                        }
                    }
                }
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