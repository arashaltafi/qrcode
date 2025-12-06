package ir.arash.altafi.qrcode.ui.component

import android.app.AlertDialog
import android.content.Context
import ir.arash.altafi.qrcode.utils.ext.copyToClipboard
import ir.arash.altafi.qrcode.utils.ext.openURL

fun showResultDialog(
    context: Context,
    text: String,
    onContinue: () -> Unit
) {
    AlertDialog.Builder(context)
        .setTitle("QR Result")
        .setMessage(text)
        .setPositiveButton("Open URL") { _, _ ->
            try {
                context.openURL(text)
            } catch (_: Exception) {
            }
            onContinue()
        }
        .setNegativeButton("Copy") { _, _ ->
            context.copyToClipboard(text)
            onContinue()
        }
        .setNeutralButton("Close") { _, _ -> onContinue() }
        .show()
}
