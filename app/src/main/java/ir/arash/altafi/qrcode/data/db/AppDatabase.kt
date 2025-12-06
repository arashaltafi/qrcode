package ir.arash.altafi.qrcode.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.arash.altafi.qrcode.data.model.TestEntity

@Database(
    entities = [TestEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun testDao(): TestDao

}