package ir.arash.altafi.qrcode.data.repository

import ir.arash.altafi.qrcode.data.db.QRCodeDao
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.utils.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteRepository @Inject constructor(
    private val qrCodeDao: QRCodeDao,
) : BaseRepository() {

    suspend fun delete(qrcode: QRCodeEntity) {
        qrCodeDao.removeQrCode(qrcode)
    }
}