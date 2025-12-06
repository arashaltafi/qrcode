package ir.arash.altafi.qrcode.ui.page.scan

import android.Manifest
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.journeyapps.barcodescanner.CompoundBarcodeView
import ir.arash.altafi.qrcode.ui.component.showResultDialog

@Composable
fun ScanScreen() {
    val context = LocalContext.current

    // ---------- CAMERA PERMISSION ----------
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
        // You can show UI text "Need Camera Permission"
        return
    }

    // ----------- ZXING CAMERA VIEW -----------
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { ctx ->
            val scanner = CompoundBarcodeView(ctx)
            scanner.initializeFromIntent(Intent())
            scanner.resume()      // IMPORTANT: start camera here

            scanner.decodeContinuous { result ->
                scanner.pause()

                showResultDialog(context, result.text) {
                    scanner.resume()
                }
            }

            scanner
        }
    )
}