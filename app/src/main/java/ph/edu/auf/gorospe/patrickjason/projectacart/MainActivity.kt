package ph.edu.auf.gorospe.patrickjason.projectacart

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.FirebaseApp
import ph.edu.auf.gorospe.patrickjason.projectacart.navigation.AppNavigation
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.BottomNavigationBar
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.loginscreen.LoginScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.RegistrationScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.welcomescreen.WelcomeScreen
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextFieldDark
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextFieldLight
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.topappbar.MyTopAppBar
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.MainScreen

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        enableEdgeToEdge()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }

        setContent {
            AppTheme {
                val navController = rememberNavController()
                //SetBarColor(color = AppTheme.colorScheme.background)
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(navController = navController)
                }
            }
        }
    }
    @Composable
    //SET ANDROID STATUS BAR COLOR
    private fun SetBarColor(color: Color){
        val systemUIController = rememberSystemUiController()
        SideEffect {
            systemUIController.setSystemBarsColor(color = color)
        }
    }
}