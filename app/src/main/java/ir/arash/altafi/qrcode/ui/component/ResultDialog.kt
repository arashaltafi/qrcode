package ir.arash.altafi.qrcode.ui.component

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri

fun showResultDialog(context: Context, text: String, onContinue: () -> Unit) {
    AlertDialog.Builder(context)
        .setTitle("QR Result")
        .setMessage(text)
        .setPositiveButton("Open URL") { _, _ ->
            try {
                val intent = Intent(Intent.ACTION_VIEW, text.toUri())
                context.startActivity(intent)
            } catch (e: Exception) {}
            onContinue()
        }
        .setNegativeButton("Copy") { _, _ ->
            val clip = ClipData.newPlainText("qr", text)
            val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(clip)
            Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show()
            onContinue()
        }
        .setNeutralButton("Close") { _, _ -> onContinue() }
        .show()
}
