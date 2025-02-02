package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.impl.AccountServiceImpl
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.SecondaryAltButton
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

@Composable
fun WelcomeScreen(navController: NavController) {
    // Check if dark theme is active
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundImage = if (isDarkTheme) R.drawable.bg_dark else R.drawable.bg_light

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = backgroundImage), // Replace with your background image resource
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop // Adjusts image to cover the entire screen
        )

        // Foreground Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom, // Align content to the bottom
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title, Subtitle, and Buttons grouped together
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Start)  // Added alignment to ensure logo stays left-aligned
                )
                Text(
                    text = "Plan your next commute",
                    style = AppTheme.typography.h1,
                    fontSize = 40.sp,
                    color = AppTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Navigate Angeles City's public transport routes with confidence - we've got your commute covered.",
                    style = AppTheme.typography.body1,
                    color = AppTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(32.dp))

                PrimaryButton(
                    label = "Log in",
                    onClick = { navController.navigate("login") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                SecondaryAltButton(
                    label = "Create account",
                    onClick = { navController.navigate("registration") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

