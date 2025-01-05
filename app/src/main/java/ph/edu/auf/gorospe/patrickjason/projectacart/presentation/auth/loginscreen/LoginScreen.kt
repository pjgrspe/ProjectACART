package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.auth.loginscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.PrimaryButton
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField

@Composable
fun LoginScreen(
//    onLogin: () -> Unit
    navcontroller: NavController
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        // Username Field
        StyledTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            leadingIcon = Icons.Default.Person,
            leadingIconContentDescription = "Username Icon"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        StyledTextField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            leadingIcon = Icons.Default.Lock,
            leadingIconContentDescription = "Password Icon",
//            isPassword = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        PrimaryButton(
            label = "Login",
            onClick = { /*onLogin()*/ }
        )

        //Debug Registration Nav button
        PrimaryButton(
            label = "Navigate to Registration",
            onClick = { navcontroller.navigate("registration") }
        )

        //Debug Main Screen Nav Button
        PrimaryButton(
            label = "Navigate to Main",
            onClick = { navcontroller.navigate("main") }
        )
    }
}