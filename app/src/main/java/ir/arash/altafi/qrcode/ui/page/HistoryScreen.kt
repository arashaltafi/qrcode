package ir.arash.altafi.qrcode.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class QRCodeEntity(
    val text: String,
    val time: Long
)

@Composable
fun HistoryScreen() {

    val list = List(10) {
        QRCodeEntity(
            text = "Text $it",
            time = System.currentTimeMillis()
        )
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        items(list.size) { count ->
            val item = list[count]

            Column(Modifier.padding(12.dp)) {
                Text(
                    text = item.text,
                    fontSize = 18.sp
                )
                Text(
                    text = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
                        .format(Date(item.time)),
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                HorizontalDivider()
            }
        }
    }
}