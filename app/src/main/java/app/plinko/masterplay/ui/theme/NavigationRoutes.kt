package app.plinko.masterplay.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.plinko.masterplay.ui.theme.displays.GamePresent
import app.plinko.masterplay.ui.theme.displays.HelpPresent
import app.plinko.masterplay.ui.theme.displays.LoadingPresent
import app.plinko.masterplay.ui.theme.displays.MenuPresent
import app.plinko.masterplay.ui.theme.displays.SettingsPresent


@Composable
fun NavigationRoutes(delay: Long){

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Destinations.Loading.route){

        composable(route = Destinations.Loading.route){
            LoadingPresent(navHostController = navHostController, delay = delay)
        }

        composable(route = Destinations.Menu.route){
            MenuPresent(navHostController = navHostController)

        }

        composable(route = Destinations.GamePlay.route){
            GamePresent(navHostController = navHostController)
        }

        composable(route = Destinations.Settings.route){
            SettingsPresent(navHostController = navHostController)
        }

        composable(route = Destinations.Help.route){
            HelpPresent(navHostController = navHostController)
        }

    }

}