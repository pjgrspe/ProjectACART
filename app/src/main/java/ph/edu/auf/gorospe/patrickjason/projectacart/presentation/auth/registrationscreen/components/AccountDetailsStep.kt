package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField

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
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Name Field
        StyledTextField(
            value = name,
            onValueChange = onNameChange,
            label = "Name",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Username Field
        StyledTextField(
            value = username,
            onValueChange = onUsernameChange,
            label = "Username",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email Field
        StyledTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "Email",
            leadingIcon = Icons.Default.Email,
            leadingIconContentDescription = "Email Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        StyledTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Re-enter Password Field
        StyledTextField(
            value = confirmpassword,
            onValueChange = onConfirmPasswordChange,
            label = "Re-enter Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phone Number Field
        StyledTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            label = "Phone Number",
            leadingIcon = Icons.Default.Phone,
            leadingIconContentDescription = "Phone Number Icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        var isChecked by remember { mutableStateOf(false) }
        // Terms and Conditions Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text("I accept the Terms and Privacy Policy")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Next Button using custom PrimaryButton
        PrimaryButton(
            label = "Continue",
            onClick = onNext
        )
    }
}