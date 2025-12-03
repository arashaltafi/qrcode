package ir.arash.altafi.qrcode.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import ir.arash.altafi.qrcode.ui.page.CreateScreen
import ir.arash.altafi.qrcode.ui.page.HistoryScreen
import ir.arash.altafi.qrcode.ui.page.HomeScreen
import ir.arash.altafi.qrcode.ui.page.ScanScreen
import ir.arash.altafi.qrcode.ui.page.SplashScreen
import ir.arash.altafi.qrcode.ui.theme.QrCodeTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    QrCodeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.Splash,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                dynamicComposable<Route.Splash>(
                    transitionType = TransitionType.NONE
                ) { args, backStackEntry ->
                    SplashScreen(
                        navController = navController,
                    )
                }
                dynamicComposable<Route.Home>(
                    transitionType = TransitionType.NONE
                ) { args, backStackEntry ->
                    HomeScreen(
                        navController = navController,
                    )
                }
                dynamicComposable<Route.Scan>(
                    transitionType = TransitionType.NONE
                ) { args, backStackEntry ->
                    ScanScreen()
                }
                dynamicComposable<Route.Create>(
                    transitionType = TransitionType.NONE
                ) { args, backStackEntry ->
                    CreateScreen()
                }
                dynamicComposable<Route.History>(
                    transitionType = TransitionType.NONE
                ) { args, backStackEntry ->
                    HistoryScreen()
                }
            }
        }
    }
}