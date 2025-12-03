package ir.arash.altafi.qrcode.ui.page

import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ir.arash.altafi.qrcode.utils.Utils

@Composable
fun CreateScreen() {
    var text by remember { mutableStateOf("") }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    Column(Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { qrBitmap = Utils.generateQrBitmap(text) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Generate QR") }

        Spacer(Modifier.height(20.dp))

        qrBitmap?.let { bmp ->
            Image(
                bitmap = bmp.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val name = "qr_${System.currentTimeMillis()}.png"
                    MediaStore.Images.Media.insertImage(
                        context.contentResolver,
                        bmp,
                        name,
                        "QR Code Image"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Download QR Image")
            }
        }
    }
}
