package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.R

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "App Logo",
            tint = Color.Black
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* TODO: Notifications */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { /* TODO: User Profile */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "User Profile",
                    tint = Color.Black
                )
            }
        }
    }
}