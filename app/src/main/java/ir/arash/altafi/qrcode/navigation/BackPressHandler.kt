package ir.arash.altafi.qrcode.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BackPressHandler(
    navController: NavController,
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    val activity = (context as? Activity)
    val packageName = context.packageName

    val isHome = currentDestination == packageName + Route.Home.route

    BackHandler {
        if (isHome) {
            activity?.finish()
        } else {
            navController.popBackStack()
        }
    }
}
