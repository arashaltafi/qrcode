package ir.arash.altafi.qrcode.ui.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.ui.component.LoadingIndicatorType
import ir.arash.altafi.qrcode.ui.component.LoadingIndicators
import ir.arash.altafi.qrcode.ui.page.history.DeleteViewModel
import ir.arash.altafi.qrcode.utils.Utils
import ir.arash.altafi.qrcode.utils.base.ApiState
import ir.arash.altafi.qrcode.utils.base.BaseScreen
import ir.arash.altafi.qrcode.utils.ext.toast

@Composable
fun DetailScreen(
    detailViewModel: DetailViewModel = hiltViewModel(),
    deleteViewModel: DeleteViewModel = hiltViewModel(),
    id: Int,
    navController: NavHostController,
) {
    val context = LocalContext.current

    val detailViewModelApiState by detailViewModel.apiState.collectAsState()

    LaunchedEffect(id) {
        if (id > 0) {
            detailViewModel.getById(id)
        } else {
            navController.popBackStack()
            context.toast("Invalid ID")
        }
    }

    BaseScreen(
        onRefresh = {
            detailViewModel.getById(id)
        }
    ) {
        when (detailViewModelApiState) {
            is ApiState.Success -> {
                val qrCode = (detailViewModelApiState as ApiState.Success).data

                DetailContent(
                    data = qrCode,
                    onDelete = {
                        deleteViewModel.delete(qrCode)
                        context.toast("Deleted")
                        navController.popBackStack()
                    },
                    onDownload = {
                        Utils.downloadBitmap(
                            context = context,
                            bmp = qrCode.bitmap,
                            name = qrCode.text
                        )
                    },
                    onShare = {
                        Utils.shareImage(
                            context = context,
                            title = qrCode.text,
                            bitmap = qrCode.bitmap
                        )
                    }
                )
            }

            is ApiState.Error -> {
                context.toast((detailViewModelApiState as ApiState.Error).message)
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

@Composable
private fun DetailContent(
    data: QRCodeEntity,
    onDelete: () -> Unit,
    onDownload: () -> Unit,
    onShare: () -> Unit
) {
    val formattedTime = remember(data.time) {
        Utils.formatFullDateTime(data.time)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    bitmap = data.bitmap.asImageBitmap(),
                    contentScale = ContentScale.Fit,
                    contentDescription = "QR Code",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = data.text,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Created: $formattedTime",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                color = Color.Gray,
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            Button(
                onClick = onDownload,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.ContentCopy, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Download")
            }

            Button(
                onClick = onShare,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Share, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Share")
            }

            Button(
                onClick = onDelete,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Delete")
            }
        }
    }
}