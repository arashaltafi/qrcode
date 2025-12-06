package ir.arash.altafi.qrcode.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.arash.altafi.qrcode.R

@Composable
fun EmptyLayout() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieComponent(
                size = DpSize(width = 180.dp, height = 180.dp),
                loop = true,
                reverseOnRepeat = false,
                lottieFile = R.raw.empty_list,
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "No Data",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

    }
}