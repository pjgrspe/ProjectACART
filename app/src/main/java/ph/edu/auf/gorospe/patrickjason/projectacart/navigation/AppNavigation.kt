package ph.edu.auf.gorospe.patrickjason.projectacart.navigation

import MainScreen
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.AccountService
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.loginscreen.LoginScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.RegistrationScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.welcomescreen.WelcomeScreen

@Composable
fun AppNavigation(navController: NavHostController, accountService: AccountService) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = if (!accountService.hasUser()) "welcome" else "main") {
        composable("welcome") {
            if (accountService.hasUser()) {
                LaunchedEffect(Unit) {
                    navController.navigate("main") {
                        //popUpTo("welcome") { inclusive = true }
                        popUpTo(0)
                    }
                }
            } else {
                WelcomeScreen(navController)
            }
        }
        composable("login") {
            if (accountService.hasUser()) {
                LaunchedEffect(Unit) {
                    navController.navigate("main") {
                        //popUpTo("login") { inclusive = true }
                        popUpTo(0)
                    }
                }
            } else {
                LoginScreen(navController)
            }
        }
        composable("registration") {
            if (accountService.hasUser()) {
                LaunchedEffect(Unit) {
                    navController.navigate("main") {
                        //popUpTo("registration") { inclusive = true }
                        popUpTo(0)
                    }
                }
            } else {
                RegistrationScreen(navController)
            }
        }
        composable("main") {
            if (!accountService.hasUser()) {
                LaunchedEffect(Unit) {
                    navController.navigate("welcome") {
                        //popUpTo("main") { inclusive = true }
                        popUpTo(0)
                    }
                }
            } else {
                MainScreen(navController)
            }
        }
    }
}