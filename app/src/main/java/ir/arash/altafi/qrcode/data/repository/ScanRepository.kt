package ir.arash.altafi.qrcode.data.repository

import ir.arash.altafi.qrcode.data.db.QRCodeDao
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.utils.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScanRepository @Inject constructor(
    private val qrCodeDao: QRCodeDao,
) : BaseRepository() {

    suspend fun addQrCode(text: String, time: Long): Boolean {
        val qrCode = QRCodeEntity(
            text = text,
            time = time
        )
        val result = qrCodeDao.insertQrCode(qrCode)
        return result > 0
    }
}