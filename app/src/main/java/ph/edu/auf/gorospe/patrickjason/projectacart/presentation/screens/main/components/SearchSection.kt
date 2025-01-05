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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Black, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        TextField(
            value = "Current Location",
            onValueChange = {},
            label = { Text("Pickup Point") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = "Where are you going?",
            onValueChange = {},
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("14:00", color = Color.White)
            IconButton(onClick = { /* TODO: Settings */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Settings",
                    tint = Color.White
                )
            }
        }
    }
}