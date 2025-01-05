package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.registrationscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField

@Composable
fun AccountDetailsStep(onNext: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Name Field
        StyledTextField(
            value = "",
            onValueChange = { /* Handle username input */ },
            label = "Name",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))


        // Username Field
        StyledTextField(
            value = "",
            onValueChange = { /* Handle username input */ },
            label = "Username",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        StyledTextField(
            value = "",
            onValueChange = { /* Handle password input */ },
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Re-enter Password Field
        StyledTextField(
            value = "",
            onValueChange = { /* Handle password input */ },
            label = "Re-enter Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Phone Number Field
        StyledTextField(
            value = "",
            onValueChange = { /* Handle phone number input */ },
            label = "Phone Number",
            leadingIcon = Icons.Default.Phone,
            leadingIconContentDescription = "Phone Number Icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Terms and Conditions Checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = { /* Handle checkbox */ })
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