package ph.edu.auf.gorospe.patrickjason.projectacart.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.loginscreen.LoginScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.RegistrationScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.welcomescreen.WelcomeScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("registration") {
            RegistrationScreen(navController)
        }
        composable("main") {
            MainScreen(navController)
        }
    }
}