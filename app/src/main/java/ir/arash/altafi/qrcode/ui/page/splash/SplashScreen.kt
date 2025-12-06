package ir.arash.altafi.qrcode.ui.page.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ir.arash.altafi.qrcode.R
import ir.arash.altafi.qrcode.ui.component.TypewriterText
import ir.arash.altafi.qrcode.navigation.Route
import ir.arash.altafi.qrcode.ui.theme.*
import kotlinx.coroutines.delay
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import ir.arash.altafi.qrcode.ui.component.LoadingIndicatorType
import ir.arash.altafi.qrcode.ui.component.LoadingIndicators

@Composable
fun SplashScreen(
    navController: NavController,
) {
    val context = LocalContext.current

    val versionName =
        context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "1.0"

    LaunchedEffect(Unit) {
        delay(3000)

        navController.navigate(Route.Home) {
            popUpTo(Route.Splash) {
                saveState = true
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Blue300, Blue400, Blue500)
                )
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TypewriterText(
            modifier = Modifier.padding(top = 32.dp),
            text = stringResource(R.string.app_name),
            color = White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        LoadingIndicators(
            isInfinite = true,
            type = LoadingIndicatorType.LINEAR_WAVY,
        )


        Text(
            modifier = Modifier.padding(bottom = 32.dp),
            text = versionName,
            color = White,
        )
    }
}