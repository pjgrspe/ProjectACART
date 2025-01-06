package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.screens.main.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.model.service.AccountService

@Composable
fun TopBar(accountService: AccountService, coroutineScope: CoroutineScope, navController: NavController, context: Context) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // App Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .size(80.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Notification Icon
            IconButton(onClick = { /* TODO: Notifications */ }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )
            }

            // User Profile Icon
            IconButton(onClick = {
                coroutineScope.launch {
                    accountService.signOut(
                        onSuccess = {
                            Toast.makeText(context, "User Signed Out", Toast.LENGTH_SHORT).show()
                            navController.navigate("welcome")
                        },
                        onFailure = { Toast.makeText(context, "Error Signing out", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "User Profile",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}
