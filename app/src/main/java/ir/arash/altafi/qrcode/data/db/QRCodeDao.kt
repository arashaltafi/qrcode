package ir.arash.altafi.qrcode.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.arash.altafi.qrcode.ui.page.history.QRCodeEntity

@Dao
interface QRCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQrCode(test: QRCodeEntity)

    @Delete
    suspend fun removeQrCode(test: QRCodeEntity)

    @Query("SELECT * FROM test WHERE id = :id")
    suspend fun getQrCodeById(id: String): QRCodeEntity?

    @Query("SELECT * FROM test")
    suspend fun getAllQrCodes(): List<QRCodeEntity>
}