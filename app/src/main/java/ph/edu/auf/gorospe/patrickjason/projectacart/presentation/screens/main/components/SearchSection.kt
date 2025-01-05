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
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import android.location.Geocoder
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(context: Context) {
    var pickupPoint by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    LaunchedEffect(Unit) {
        getCurrentLocation(fusedLocationClient, context) { address ->
            pickupPoint = address
        }
    }

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

@SuppressLint("MissingPermission")
fun getCurrentLocation(fusedLocationClient: FusedLocationProviderClient, context: Context, onAddressReceived: (String) -> Unit) {
    fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
        location?.let {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            val address = addresses?.get(0)?.getAddressLine(0) ?: "Unknown location"
            onAddressReceived(address)
        }
    }
}