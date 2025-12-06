package ir.arash.altafi.qrcode.data.db

import androidx.room.*
import ir.arash.altafi.qrcode.data.model.TestEntity

@Dao
interface TestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTest(test: TestEntity)

    @Delete
    suspend fun removeTest(test: TestEntity)

    @Query("SELECT * FROM test WHERE id = :id")
    suspend fun getTestById(id: String): TestEntity?

    @Query("SELECT * FROM test")
    suspend fun getAllTests(): List<TestEntity>
}