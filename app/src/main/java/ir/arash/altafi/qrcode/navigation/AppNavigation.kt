package ir.arash.altafi.qrcode.navigation

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*
import ir.arash.altafi.qrcode.R
import ir.arash.altafi.qrcode.ui.page.create.CreateScreen
import ir.arash.altafi.qrcode.ui.page.history.HistoryScreen
import ir.arash.altafi.qrcode.ui.page.home.HomeScreen
import ir.arash.altafi.qrcode.ui.page.scan.ScanScreen
import ir.arash.altafi.qrcode.ui.page.splash.SplashScreen
import ir.arash.altafi.qrcode.ui.theme.Blue700
import ir.arash.altafi.qrcode.ui.theme.QrCodeTheme
import ir.arash.altafi.qrcode.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val packageName = context.packageName

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val isHome = currentDestination == packageName + Route.Home.route

    QrCodeTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    modifier = Modifier
                        .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)),
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            color = White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Blue700,
                        titleContentColor = White,
                    ),
                    actions = {
                        IconButton(
                            onClick = {
                                if (isHome) {
                                    activity?.finish()
                                } else {
                                    navController.popBackStack()
                                }
                            }
                        ) {
                            Icon(
                                modifier = Modifier.rotate(180f),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = White
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
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

            BackPressHandler(navController)
        }
    }
}