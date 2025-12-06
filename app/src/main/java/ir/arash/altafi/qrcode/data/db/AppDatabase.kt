package ir.arash.altafi.qrcode.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ir.arash.altafi.qrcode.data.model.QRCodeEntity
import ir.arash.altafi.qrcode.data.model.TestEntity

@Database(
    entities = [TestEntity::class, QRCodeEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(BitmapConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao
    abstract fun qrCodeDao(): QRCodeDao

}