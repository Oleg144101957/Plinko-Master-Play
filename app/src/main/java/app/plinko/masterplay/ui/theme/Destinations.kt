package app.plinko.masterplay.ui.theme

sealed class Destinations(val route: String){

    object Loading : Destinations("loading")
    object Menu : Destinations("menu")
    object GamePlay : Destinations("gameplay")
    object Help : Destinations("help")
    object Settings : Destinations("settings")

}