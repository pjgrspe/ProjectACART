package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.impl.AccountServiceImpl
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.components.AccountDetailsStep
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.components.ProfilePictureStep
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.components.ProgressIndicator

@Composable
fun RegistrationScreen(
//    onRegister: () -> Unit
    navController: NavController
) {
    val accountService = AccountServiceImpl(FirebaseFirestore.getInstance())
    val coroutineScope = rememberCoroutineScope()
    var currentStep by remember { mutableStateOf(1) }
    val totalSteps = 2  // Adjust if you add more steps
    val stepTitles = listOf(
        "Account Details",
        "Profile Picture"
    )

    //Firebase Auth
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    //Registration Data
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var profilePictureBitmap by remember { mutableStateOf<Bitmap?>(null) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Progress Indicator at the top
        ProgressIndicator(currentStep, totalSteps)
        // Alternatively, use HorizontalProgressIndicator
        // HorizontalProgressIndicator(currentStep, totalSteps)
        // Step title at the top
        Text(
            text = stepTitles.getOrNull(currentStep - 1) ?: "Registration",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Display different steps
        when (currentStep) {
            1 -> AccountDetailsStep(
                onNext = { currentStep++ },
                name = name,
                onNameChange = { name = it },
                username = username,
                onUsernameChange = { username = it },
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                confirmpassword = confirmPassword,
                onConfirmPasswordChange = { confirmPassword = it },
                phoneNumber = phoneNumber,
                onPhoneNumberChange = { phoneNumber = it }
            )
            2 -> ProfilePictureStep(
                onPrevious = { currentStep-- },
                onNext = {bitmap ->
                    profilePictureBitmap = bitmap
                    //currentStep++
                    coroutineScope.launch {
                        accountService.registerUser(
                            name,
                            username,
                            email,
                            password,
                            phoneNumber,
                            profilePictureBitmap,
                            onSuccess = { navController.navigate("main") },
                            onFailure = { /* Handle error */ }
                        )
                    }
                }
            )
        }
    }
}