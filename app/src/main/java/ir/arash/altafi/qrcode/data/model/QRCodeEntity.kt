package ir.arash.altafi.qrcode.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "qrcode")
@Parcelize
data class QRCodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @SerializedName("text")
    val text: String,
    @SerializedName("time")
    val time: Long
) : Parcelable