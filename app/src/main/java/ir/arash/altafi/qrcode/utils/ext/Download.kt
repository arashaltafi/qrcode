package ir.arash.altafi.qrcode.utils.ext

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

fun Context.saveImageBlow29(bitmap: Bitmap, isSaveSuccess: (Boolean) -> Unit) {
    val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    val folderName = "reminder"
    val imageName = "${System.currentTimeMillis()}.jpg"
    val folder = File(downloadDir, folderName)
    if (!folder.exists()) {
        folder.mkdirs()
    }

    val file = File(folder, imageName)

    var outputStream: OutputStream? = null
    try {
        outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()

        // Notify the system that a new file was added to the Downloads directory
        MediaScannerConnection.scanFile(this, arrayOf(file.absolutePath), null, null)
        isSaveSuccess.invoke(true)
    } catch (_: Exception) {
        isSaveSuccess.invoke(false)
    } finally {
        try {
            outputStream?.close()
        } catch (_: Exception) {
            isSaveSuccess.invoke(false)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
fun Context.saveImage(bitmap: Bitmap, isSaveSuccess: (Boolean) -> Unit) {
    val resolver = contentResolver
    val imageName = "${System.currentTimeMillis()}.jpg"

    val values = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/reminder")
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)


    if (uri != null) {
        try {
            resolver.openOutputStream(uri)?.use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            }

            // Mark file as finished
            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(uri, values, null, null)

            isSaveSuccess(true)
        } catch (_: Exception) {
            isSaveSuccess(false)
        }
    } else {
        isSaveSuccess(false)
    }
}