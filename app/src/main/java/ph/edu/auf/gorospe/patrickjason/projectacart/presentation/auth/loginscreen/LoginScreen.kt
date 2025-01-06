package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.loginscreen

import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField

@Composable
fun LoginScreen(navcontroller: NavController) {
    val auth = FirebaseAuth.getInstance()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    // Credentials
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Validation errors
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var isSubmitPressed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Login",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Email Field
        StyledTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Email Icon",
            error = if (isSubmitPressed) emailError else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        StyledTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon",
            trailingIcon = if (passwordVisible) Icons.Default.Lock else Icons.Default.Lock,
            trailingIconContentDescription = if (passwordVisible) "Hide Password" else "Show Password",
            onTrailingIconClick = { passwordVisible = !passwordVisible },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            error = if (isSubmitPressed) passwordError else null
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        PrimaryButton(
            label = "Login",
            onClick = {
                isSubmitPressed = true

                // Perform validations
                emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    "Invalid email address"
                } else null
                passwordError = if (password.length < 6) {
                    "Password must be at least 6 characters"
                } else null

                if (emailError == null && passwordError == null) {
                    coroutineScope.launch {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    navcontroller.navigate("main")
                                } else {
                                    showCustomErrorToast(
                                        context,
                                        "Incorrect email or password"
                                    )
                                }
                            }
                    }
                }
            }
        )

        // Debug Registration Nav Button
        PrimaryButton(
            label = "Navigate to Registration",
            onClick = { navcontroller.navigate("registration") }
        )

        // Debug Main Screen Nav Button
        PrimaryButton(
            label = "Navigate to Main",
            onClick = { navcontroller.navigate("main") }
        )
    }
}

// Custom error toast
fun showCustomErrorToast(context: android.content.Context, message: String) {
    val inflater = LayoutInflater.from(context)
    val layout = inflater.inflate(R.layout.custom_error_toast, null)

    val toast = Toast(context)
    toast.view = layout
    toast.duration = Toast.LENGTH_SHORT

    layout.findViewById<TextView>(R.id.toast_message).text = message
    toast.show()
}

