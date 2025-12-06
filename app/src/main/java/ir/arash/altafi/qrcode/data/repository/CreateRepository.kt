package ir.arash.altafi.qrcode.data.repository

import android.graphics.Bitmap
import ir.arash.altafi.qrcode.data.db.QRCodeDao
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.utils.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateRepository @Inject constructor(
    private val qrCodeDao: QRCodeDao,
) : BaseRepository() {

    suspend fun addQrCode(
        text: String,
        bitmap: Bitmap,
        time: Long
    ): Boolean {
        val qrCode = QRCodeEntity(
            text = text,
            bitmap = bitmap,
            time = time,
        )
        val result = qrCodeDao.insertQrCode(qrCode)
        return result > 0
    }
}