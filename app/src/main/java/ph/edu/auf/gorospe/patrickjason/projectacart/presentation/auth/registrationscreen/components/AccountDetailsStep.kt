package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.components

import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField

// Extension function to check for special characters
fun String.containsSpecialCharacters(): Boolean {
    val specialChars = """[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>/?]""".toRegex()
    return this.contains(specialChars)
}

// Extension function to check if string contains only letters and spaces
fun String.containsOnlyLettersAndSpaces(): Boolean {
    return this.all { it.isLetter() || it.isWhitespace() }
}

@Composable
fun AccountDetailsStep(
    onNext: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    username: String,
    onUsernameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    confirmpassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()

    // Validation errors
    var nameError by remember { mutableStateOf<String?>(null) }
    var usernameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var confirmPasswordError by remember { mutableStateOf<String?>(null) }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var isSubmitPressed by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val isFormValid = name.isNotBlank() &&
            name.containsOnlyLettersAndSpaces() &&
            username.length >= 6 &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.length >= 6 &&
            password.any { it.isUpperCase() } &&
            password.any { it.isLowerCase() } &&
            password == confirmpassword &&
            phoneNumber.length in 10..15 &&
            phoneNumber.all { it.isDigit() } &&
            isChecked

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        StyledTextField(
            value = name,
            onValueChange = { newValue ->
                if (newValue.containsOnlyLettersAndSpaces() || newValue.isEmpty()) {
                    onNameChange(newValue)
                }
            },
            label = "Name",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Name Icon",
            error = if (isSubmitPressed) nameError else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        StyledTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = "Username",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon",
            error = if (isSubmitPressed) usernameError else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        StyledTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            leadingIcon = Icons.Default.Email,
            leadingIconContentDescription = "Email Icon",
            error = if (isSubmitPressed) emailError else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        StyledTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon",
            trailingIcon = if (passwordVisible) ImageVector.vectorResource(id = R.drawable.eye) else ImageVector.vectorResource(id = R.drawable.eye),
            trailingIconContentDescription = if (passwordVisible) "Hide Password" else "Show Password",
            onTrailingIconClick = { passwordVisible = !passwordVisible },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            error = if (isSubmitPressed) passwordError else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Confirm Password Field
        StyledTextField(
            value = confirmpassword,
            onValueChange = onConfirmPasswordChange,
            label = "Re-enter Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon",
            trailingIcon = if (confirmPasswordVisible) ImageVector.vectorResource(id = R.drawable.eye) else ImageVector.vectorResource(id = R.drawable.eye),
            trailingIconContentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password",
            onTrailingIconClick = { confirmPasswordVisible = !confirmPasswordVisible },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            error = if (isSubmitPressed) confirmPasswordError else null
        )

        Spacer(modifier = Modifier.height(8.dp))

        StyledTextField(
            value = phoneNumber,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() } || newValue.isEmpty()) {
                    onPhoneNumberChange(newValue)
                }
            },
            label = "Phone Number",
            leadingIcon = Icons.Default.Phone,
            leadingIconContentDescription = "Phone Number Icon",
            error = if (isSubmitPressed) phoneNumberError else null
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text("I accept the Terms and Privacy Policy")
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            label = "Continue",
            onClick = {
                isSubmitPressed = true

                nameError = when {
                    name.isBlank() -> "Name cannot be empty"
                    !name.containsOnlyLettersAndSpaces() -> "Name can only contain letters"
                    else -> null
                }

                usernameError = if (username.length < 6) "Username must be at least 6 characters" else null
                emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    "Invalid email address"
                } else null
                passwordError = when {
                    password.length < 6 -> "Password must be at least 6 characters"
                    !password.any { it.isUpperCase() } -> "Password must contain an uppercase letter"
                    !password.any { it.isLowerCase() } -> "Password must contain a lowercase letter"
                    else -> null
                }
                confirmPasswordError = if (password != confirmpassword) "Passwords do not match" else null
                phoneNumberError = when {
                    phoneNumber.isEmpty() -> "Phone number is required"
                    !phoneNumber.all { it.isDigit() } -> "Phone number must contain only digits"
                    phoneNumber.length !in 10..15 -> "Phone number must be 10-15 digits"
                    else -> null
                }

                if (!isChecked) {
                    showCustomErrorToast(context, "You must accept the Terms and Privacy Policy")
                }

                if (isFormValid) {
                    onNext()
                }
            },
            enabled = true
        )
        Spacer(modifier = Modifier.height(16.dp))
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