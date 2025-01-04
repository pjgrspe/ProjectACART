package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
){
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled && !loading,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colorScheme.primary,
            contentColor = AppTheme.colorScheme.onPrimary
        ),
        shape = AppTheme.shapes.button
    ) {
        if (loading) {
            // Display a loading indicator (e.g., CircularProgressIndicator)
            CircularProgressIndicator(
                color = AppTheme.colorScheme.onPrimary,
                modifier = Modifier.size(AppTheme.sizes.medium)
            )
        } else {
            Text(
                text = label,
                style = AppTheme.typography.button,
                color = AppTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
){
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled && !loading,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = AppTheme.colorScheme.primaryDark,
            contentColor = AppTheme.colorScheme.onPrimaryDark
        ),
        shape = AppTheme.shapes.button
    ) {
        if (loading) {
            // Display a loading indicator (e.g., CircularProgressIndicator)
            CircularProgressIndicator(
                color = AppTheme.colorScheme.onPrimaryDark,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Text(
                text = label,
                style = AppTheme.typography.button,
                color = AppTheme.colorScheme.onPrimaryDark,
            )
        }
    }
}

@Composable
fun SecondaryAltButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
){
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled && !loading,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = AppTheme.colorScheme.background,
            contentColor = AppTheme.colorScheme.onBackground
        ),
        shape = AppTheme.shapes.button,
        border = BorderStroke(1.dp, AppTheme.colorScheme.onBackground)
    ) {
        if (loading) {
            // Display a loading indicator (e.g., CircularProgressIndicator)
            CircularProgressIndicator(
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Text(
                text = label,
                style = AppTheme.typography.button,
                color = AppTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
){
    Button(
        modifier = modifier.size(48.dp), // Fixed size for icon button
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        enabled = enabled && !loading,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colorScheme.secondary,
            contentColor = AppTheme.colorScheme.onSecondary
        ),
        shape = AppTheme.shapes.button
    ) {
        if (loading) {
            CircularProgressIndicator(
                color = AppTheme.colorScheme.onSecondary,
                modifier = Modifier.size(16.dp)
            )
        } else {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = AppTheme.colorScheme.onSecondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ClickableText(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    color: androidx.compose.ui.graphics.Color = AppTheme.colorScheme.primary
) {
    Text(
        text = text,
        modifier = modifier
            .padding(AppTheme.sizes.small)
            .clickable(enabled = enabled, onClick = onClick),
        color = if (enabled) color else color.copy(alpha = 0.5f),
        style = AppTheme.typography.button
    )
}

@Preview(showBackground = true)
@Composable
fun ClickableTextPreview() {
    AppTheme {
        Column(
            modifier = Modifier
                .padding(AppTheme.sizes.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.sizes.normal)
        ) {
            ClickableText(
                text = "Click Me",
                onClick = { /* Your action here */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    AppTheme {
        Column (
            modifier = Modifier
                .padding(AppTheme.sizes.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.sizes.normal)
        ){
            PrimaryButton(
                label = "Primary Button",
                onClick = {}
            )
            SecondaryButton(
                label = "Secondary Button",
                onClick = {}
            )
            SecondaryAltButton(
                label = "Secondary Button",
                onClick = {}
            )
            // Example usage of TertiaryButton with an icon
            // You'll need to import the specific icon you want to use
            // import androidx.compose.material.icons.Icons
            // import androidx.compose.material.icons.filled.Add
            ActionButton(
                 icon = Icons.Default.Done,
                 contentDescription = "Add",
                 onClick = {}
            )
        }

    }
}