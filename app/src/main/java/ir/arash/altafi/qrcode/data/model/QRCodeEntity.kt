package ir.arash.altafi.qrcode.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qrcode")
data class QRCodeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val text: String,
    val time: Long
)