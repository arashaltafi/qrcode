package ir.arash.altafi.qrcode.data.repository

import ir.arash.altafi.qrcode.data.db.TestDao
import ir.arash.altafi.qrcode.data.model.TestEntity
import ir.arash.altafi.qrcode.utils.base.BaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val dao: TestDao,
) : BaseRepository() {

    suspend fun getAll(): List<TestEntity>? {
        return dao.getAllTests()
    }
}