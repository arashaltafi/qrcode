package ir.arash.altafi.qrcode.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.arash.altafi.qrcode.navigation.Route
import ir.arash.altafi.qrcode.ui.component.LottieComponent
import ir.arash.altafi.qrcode.R

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        LottieComponent(
            modifier = Modifier.align(Alignment.TopCenter),
            size = DpSize(width = 180.dp, height = 180.dp),
            loop = true,
            reverseOnRepeat = false,
            lottieFile = R.raw.qrcode,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 196.dp)
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navController.navigate(Route.Scan)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Scan QR Code")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Route.Create)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Create QR Code")
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Route.History)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("History")
            }
        }
    }
}