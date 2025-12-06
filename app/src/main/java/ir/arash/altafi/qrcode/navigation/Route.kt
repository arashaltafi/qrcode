package ir.arash.altafi.qrcode.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    val route: String

    @Serializable
    data object Splash : Route {
        override val route: String = ".navigation.Route.SplashScreen"
    }

    @Serializable
    data object Home : Route {
        override val route: String = ".navigation.Route.HomeScreen"
    }

    @Serializable
    data object Scan : Route {
        override val route: String = ".navigation.Route.ScanScreen"
    }

    @Serializable
    data object History : Route {
        override val route: String = ".navigation.Route.HistoryScreen"
    }

    @Serializable
    data object Create : Route {
        override val route: String = ".navigation.Route.CreateScreen"
    }


    @Serializable
    data class Detail(val id: Int) : Route {
        override val route: String = ".navigation.Route.Detail"
    }

}