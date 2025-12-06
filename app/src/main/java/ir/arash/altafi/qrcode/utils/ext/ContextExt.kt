package ir.arash.altafi.qrcode.utils.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.Settings
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import ir.arash.altafi.qrcode.MainActivity
import ir.arash.altafi.qrcode.R
import java.io.File
import java.io.FileOutputStream

/**
 * Restart app (or go to LoginActivity)
 */
fun Context.restartApp() {
    try {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        this.startActivity(intent)
        if (this is Activity) this.finish()
    } catch (e: Exception) {
        Log.e("SERVER", "Restart failed: ${e.localizedMessage}")
    }
}

fun Context.isDarkTheme(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}

fun Context.inflateView(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(this)
        .inflate(layoutRes, null)
}

fun Context.openGoogleMap(lat: String, lng: String) {
    try {
        val strUri = "http://maps.google.com/maps?q=loc:$lat,$lng"
        val intent = Intent(Intent.ACTION_VIEW, strUri.toUri())
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
        ContextCompat.startActivity(this, intent, null)
    } catch (e: ActivityNotFoundException) {
        Log.e("openGoogleMap", "${e.message}")
    }
}

fun Context.openMap(lat: String, lng: String) {
    val intent =
        Intent(Intent.ACTION_VIEW, "http://maps.google.com/maps?q=loc:$lat,$lng".toUri())
    ContextCompat.startActivity(this, intent, null)
}

fun Context.openAppInfoSetting() {
    //redirect user to app Settings
    val i = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    i.addCategory(Intent.CATEGORY_DEFAULT)
    i.data = "package:$packageName".toUri()
    ContextCompat.startActivity(this, i, null)
}

fun Context.openURL(url: String) {
    try {
        val fullUrl = if (url.startsWith("http")) url else "http://$url"
        val intent = Intent(Intent.ACTION_VIEW, fullUrl.toUri())
        startActivity(intent)
    } catch (_: ActivityNotFoundException) {
    }
}

fun Context.openDownloadURL(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    when {
        this.isInstalled("com.android.chrome") -> intent.setPackage("com.android.chrome")
        this.isInstalled("org.mozilla.firefox") -> intent.setPackage("org.mozilla.firefox")
        this.isInstalled("com.opera.mini.android") -> intent.setPackage("com.opera.mini.android")
        this.isInstalled("com.opera.mini.android.Browser") -> intent.setPackage("com.opera.mini.android.Browser")
        else -> this.openURL(url)
    }
    startActivity(intent)
}

private fun Context.isInstalled(packageName: String): Boolean {
    return try {
        this.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    } catch (_: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.share(text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Context.shareTextWithImage(
    bitmap: Bitmap,
    title: String
) {
    val file = File(externalCacheDir, System.currentTimeMillis().toString() + ".jpg")
    val out = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
    out.close()
    val bmpUri =
        FileProvider.getUriForFile(
            this, "$packageName.fileprovider", file
        )

    val builder: StrictMode.VmPolicy.Builder = StrictMode.VmPolicy.Builder()
    StrictMode.setVmPolicy(builder.build())

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/*"
        putExtra(Intent.EXTRA_TEXT, title)
        putExtra(Intent.EXTRA_TITLE, title)
        putExtra(Intent.EXTRA_STREAM, bmpUri)
    }

    val shareIntent = Intent.createChooser(sendIntent, "Share Image")
    startActivity(shareIntent)
}

fun Context.openCall(phoneNumber: String) {
    ContextCompat.startActivity(
        this,
        Intent(
            Intent.ACTION_DIAL,
            Uri.fromParts("tel", phoneNumber, null)
        ),
        null
    )
}

fun Context.openSMS(mobile: String, body: String = "") {
    val smsIntent = Intent(Intent.ACTION_VIEW)
    smsIntent.data = "sms:$mobile".toUri()
    smsIntent.putExtra("sms_body", body)
    ContextCompat.startActivity(this, smsIntent, null)
}

fun Context.shareApp() {
    val app: ApplicationInfo = applicationContext.applicationInfo
    val filePath: String = app.sourceDir
    val intent = Intent(Intent.ACTION_SEND)

    // MIME of .apk is "application/vnd.android.package-archive".
    // but Bluetooth does not accept this. Let's use "*/*" instead.
    intent.type = "*/*"

    // Append file and send Intent
    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(File(filePath)))
    startActivity(Intent.createChooser(intent, getString(R.string.app_name)))
}

fun Context.openEmail(
    addresses: Array<String>,
    cc: Array<String> = emptyArray(),
    bcc: Array<String> = emptyArray(),
    subject: String? = null,
    message: String? = null
) {
    //https://developer.android.com/guide/components/intents-common#ComposeEmail
    val intentGoogle = Intent(Intent.ACTION_SENDTO).apply {
        data = "mailto:".toUri()
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_CC, cc)
        putExtra(Intent.EXTRA_BCC, bcc)
        putExtra(Intent.EXTRA_SUBJECT, subject ?: "")
        putExtra(Intent.EXTRA_TEXT, message ?: "")
    }

    if (intentGoogle.resolveActivity(packageManager) != null)
        startActivity(intentGoogle)
}

@ColorInt
fun Context.getAttrColor(@AttrRes attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.getAttr(attrID: Int): Int {
    val typedValue = TypedValue()
    val theme = this.theme
    theme.resolveAttribute(attrID, typedValue, true)
    return typedValue.data
}

fun Context.hasPermissions(vararg permission: String): Boolean {
    var result = true

    permission.forEach {
        val a = ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        result = result && a
    }

    return result
}


fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Context.toast(msg: Int) {
    Toast.makeText(this, getString(msg), Toast.LENGTH_SHORT).show()
}

@SuppressLint("InternalInsetResource")
fun Context.getStatusBarHeightPixel(): Int = try {
    val res = resources.getIdentifier("status_bar_height", "dimen", "android")

    resources.getDimensionPixelSize(res)
} catch (_: Exception) {
    80
}

fun Context.copyToClipboard(text: String) {
    val myClipboard: ClipboardManager? =
        ContextCompat.getSystemService(this, ClipboardManager::class.java)

    val myClip = ClipData.newPlainText("copied:", text)
    myClipboard!!.setPrimaryClip(myClip)
    toast(getString(R.string.copy_to_clipboard))
}

fun Context.otherAppsMyket() {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = "myket://developer/com.arash.altafi.documentbag".toUri()
        startActivity(intent)
    } catch (_: Exception) {
    }
}

fun Context.otherAppsCaffeBazaar() {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =
            ("bazaar://collection?slug=by_author&aid=" + "arashaltafi").toUri()
        intent.setPackage("com.farsitel.bazaar")
        startActivity(intent)
    } catch (_: Exception) {
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.addToHomeScreen(activity: Activity) {
    val shortcutManager = activity.getSystemService(ShortcutManager::class.java)

    if (shortcutManager.isRequestPinShortcutSupported) {
        val shortcutIntent = Intent(activity, MainActivity::class.java).apply {
            action = Intent.ACTION_MAIN
        }
        val shortcutInfo = ShortcutInfo.Builder(this, "123")
            .setShortLabel(getString(R.string.app_name))
            .setIcon(
                Icon.createWithResource(
                    this, R.drawable.logo
                )
            )
            .setIntent(shortcutIntent)
            .build()

        shortcutManager.requestPinShortcut(shortcutInfo, null)
    }
}

fun Context.isServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

fun Context.openAppSettings() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

@RequiresApi(Build.VERSION_CODES.S)
fun Context.openAppSettingReminder() {
    val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
    startActivity(intent)
}