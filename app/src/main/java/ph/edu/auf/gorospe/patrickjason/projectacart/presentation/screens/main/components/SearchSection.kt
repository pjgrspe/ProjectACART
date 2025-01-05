package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextField
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields.StyledTextFieldDark
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection() {
    var pickupPoint by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(AppTheme.colorScheme.onBackground, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // StyledTextField for Pickup Point
        StyledTextFieldDark(
            value = pickupPoint,
            onValueChange = { pickupPoint = it }, // Update the state
            label = "Pickup Point",
            leadingIcon = Icons.Default.Home, // Replace with a suitable icon
            leadingIconContentDescription = "Pickup Icon",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // StyledTextField for Destination
        StyledTextFieldDark(
            value = destination,
            onValueChange = { destination = it }, // Update the state
            label = "Destination",
            leadingIcon = Icons.Default.LocationOn, // Replace with a suitable icon
            leadingIconContentDescription = "Destination Icon",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = AppTheme.sizes.small),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("14:00", color = Color.White)
            IconButton(onClick = { /* TODO: Settings */ }) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Enter",
                    tint = Color.White
                )
            }
        }
    }
}