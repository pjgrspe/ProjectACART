package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ph.edu.auf.gorospe.patrickjason.projectacart.R
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons.ActionButton
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    profileImage: Painter? = null,
    showActionButton: Boolean = true // Default is true, but you can control it
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = AppTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            if (showActionButton) {
                ActionButton(
                    icon = Icons.Default.ArrowBack,
                    contentDescription = "Add",
                    onClick = {}
                )
            }
        },
        actions = {
            if (profileImage != null) {
                Image(
                    painter = profileImage,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(36.dp) // Fixed size for the profile image container
                        .clip(CircleShape) // Ensures the profile image is circular
                        .fillMaxSize() // Fills the circular container
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Composable
fun MyTopAppBarPreview() {
    MyTopAppBar(
        title = "My App",
        profileImage = painterResource(id = R.drawable.ic_logo), // replace with an actual drawable resource
        showActionButton = true // Control visibility of action button
    )
}
