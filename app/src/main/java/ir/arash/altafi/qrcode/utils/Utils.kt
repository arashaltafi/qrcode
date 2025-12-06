package ir.arash.altafi.qrcode.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {
    fun generateQrBitmap(text: String): Bitmap {
        val size = 800
        val bits = QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size)
        val bmp = createBitmap(size, size, Bitmap.Config.RGB_565)

        for (x in 0 until size) {
            for (y in 0 until size) {
                bmp[x, y] = if (bits[x, y]) Color.BLACK else Color.WHITE
            }
        }
        return bmp
    }

    fun shareImage(
        context: Context,
        title: String,
        bitmap: Bitmap
    ) {
        try {
            val cachePath = File(context.cacheDir, "shared_images")
            cachePath.mkdirs()

            val file = File(cachePath, "shared_image_${System.currentTimeMillis()}.png")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()

            val contentUri: Uri = FileProvider.getUriForFile(
                context, "${context.packageName}.provider", file
            )

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "image/*"
                putExtra(Intent.EXTRA_STREAM, contentUri)
                putExtra(Intent.EXTRA_TEXT, title)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            context.startActivity(
                Intent.createChooser(shareIntent, "Share via")
            )

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun formatFullDateTime(
        timeMillis: Long = System.currentTimeMillis(),
        isShowSecond: Boolean = false,
        locale: Locale = Locale.getDefault()
    ): String {
        val calendar = Calendar.getInstance().apply { timeInMillis = timeMillis }

        val timeFormat = if (isShowSecond) "HH:mm:ss" else "HH:mm"
        val time = SimpleDateFormat(timeFormat, locale).format(calendar.time)

        val dayOfWeek = SimpleDateFormat("EEEE", locale).format(calendar.time)
        val date = SimpleDateFormat("MM/dd/yyyy", locale).format(calendar.time)
        return "$dayOfWeek - $date - $time"
    }

    fun formatReminderDate(
        timeMillis: Long,
        locale: Locale = Locale.getDefault()
    ): String {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply { timeInMillis = timeMillis }

        val isToday = now.get(Calendar.YEAR) == target.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == target.get(Calendar.DAY_OF_YEAR)

        val isTomorrow = now.get(Calendar.YEAR) == target.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) + 1 == target.get(Calendar.DAY_OF_YEAR)

        val isYesterday = now.get(Calendar.YEAR) == target.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) - 1 == target.get(Calendar.DAY_OF_YEAR)

        val sameWeek = now.get(Calendar.WEEK_OF_YEAR) == target.get(Calendar.WEEK_OF_YEAR) &&
                now.get(Calendar.YEAR) == target.get(Calendar.YEAR)

        val time = SimpleDateFormat("HH:mm", locale).format(target.time)
        val day = SimpleDateFormat("EEEE", locale).format(target.time)
        val date = SimpleDateFormat("MM/dd/yyyy", locale).format(target.time)

        val (timeStr, dayOfWeek, dateStr) = Triple(time, day, date)

        val result = when {
            isToday -> timeStr
            isTomorrow -> "Tomorrow - $timeStr"
            isYesterday -> "Yesterday - $timeStr"
            sameWeek -> "$dayOfWeek - $timeStr"
            else -> "$dateStr - $timeStr"
        }

        return result
    }
}