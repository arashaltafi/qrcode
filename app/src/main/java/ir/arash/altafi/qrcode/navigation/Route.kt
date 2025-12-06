package ir.arash.altafi.qrcode.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {

    val route: String

    @Serializable
    data object Splash : Route {
        override val route: String = ".navigation.Route.Splash"
    }

    @Serializable
    data object Home : Route {
        override val route: String = ".navigation.Route.Home"
    }

    @Serializable
    data object Scan : Route {
        override val route: String = ".navigation.Route.Scan"
    }

    @Serializable
    data object History : Route {
        override val route: String = ".navigation.Route.History"
    }

    @Serializable
    data object Create : Route {
        override val route: String = ".navigation.Route.Create"
    }


    @Serializable
    data class Detail(val id: Int) : Route {
        override val route: String = ".navigation.Route.Detail"
    }

}