package ir.arash.altafi.qrcode.ui.page.create

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.arash.altafi.qrcode.utils.Utils
import ir.arash.altafi.qrcode.utils.ext.toast
import kotlinx.coroutines.delay

@Composable
fun CreateScreen(
    createViewModel: CreateViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current

    var text by remember { mutableStateOf("") }
    var qrBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        delay(200)
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    if (it.length <= 300) {
                        text = it
                    }
                },
                label = {
                    Text("Enter text")
                },
                placeholder = {
                    Text("Text ...")
                },
                trailingIcon = {
                    if (text.isNotEmpty() || qrBitmap != null) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier.clickable {
                                text = ""
                                qrBitmap = null
                            }
                        )
                    }
                },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        val finalText = text.trim()
                        if (finalText.isEmpty()) {
                            context.toast("Text is empty")
                        } else {
                            qrBitmap = Utils.generateQrBitmap(finalText)
                        }
                    }
                )
            )

            Button(
                onClick = {
                    val finalText = text.trim()
                    if (finalText.isEmpty()) {
                        context.toast("Text is empty")
                    } else {
                        qrBitmap = Utils.generateQrBitmap(finalText)
                    }
                },
                enabled = text.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generate QR")
            }
        }

        qrBitmap?.let { bmp ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bmp.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Button(
                    onClick = {
                        val name = "qr_${System.currentTimeMillis()}.png"
                        Utils.downloadBitmap(
                            context = context,
                            bmp = bmp,
                            name = name
                        )
                        createViewModel.addQrCode(
                            text = text,
                            bitmap = bmp,
                            time = System.currentTimeMillis()
                        )
                        context.toast("QR Code saved successfully")
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Download QR Image")
                }
            }
        }
    }
}
