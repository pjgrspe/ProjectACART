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
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme
import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.impl.AccountServiceImpl

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
                val accountService = AccountServiceImpl(FirebaseFirestore.getInstance())

                //SetBarColor(color = AppTheme.colorScheme.background)
                LaunchedEffect(Unit) {
                    if (!accountService.hasUser()) {
                        navController.navigate("welcome")
                    }
                }
                AppNavigation(navController = navController, accountService = accountService)
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