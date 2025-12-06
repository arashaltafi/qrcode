package ir.arash.altafi.qrcode.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.arash.altafi.qrcode.data.model.QRCodeEntity

@Dao
interface QRCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQrCode(qrcode: QRCodeEntity): Long

    @Delete
    suspend fun removeQrCode(qrcode: QRCodeEntity)

    @Query("SELECT * FROM qrcode")
    suspend fun getAllQrCodes(): List<QRCodeEntity>

    @Query("SELECT * FROM qrcode WHERE id = :id")
    suspend fun getQrCodeById(id: Int): QRCodeEntity?
}