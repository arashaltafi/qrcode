package ir.arash.altafi.qrcode.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.arash.altafi.qrcode.data.model.QRCodeEntity

@Database(
    entities = [QRCodeEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(BitmapConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun qrCodeDao(): QRCodeDao

}