package ir.arash.altafi.qrcode.ui.page.scan

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.journeyapps.barcodescanner.CompoundBarcodeView
import ir.arash.altafi.qrcode.ui.component.EmptyLayout
import ir.arash.altafi.qrcode.ui.component.showResultDialog

@Composable
fun ScanScreen(
    scanViewModel: ScanViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var hasPermission by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    if (!hasPermission) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            EmptyLayout()
        }
        return
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val scanner = CompoundBarcodeView(ctx)

            scanner.statusView.text = "Scan your QR Code"

            scanner.initializeFromIntent(Intent())
            scanner.resume()

            scanner.decodeContinuous { result ->
                scanner.pause()

                scanViewModel.addQrCode(
                    text = result.text,
                    bitmap = result.bitmap,
                    time = System.currentTimeMillis()
                )

                showResultDialog(
                    context = context,
                    text = result.text
                ) {
                    scanner.resume()
                }
            }

            scanner
        }
    )
}