package ir.arash.altafi.qrcode.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.arash.altafi.qrcode.navigation.Route

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
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