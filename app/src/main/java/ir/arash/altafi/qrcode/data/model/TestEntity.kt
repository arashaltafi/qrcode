package ir.arash.altafi.qrcode.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "test")
data class TestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val family: String,
    val avatar: String
)
