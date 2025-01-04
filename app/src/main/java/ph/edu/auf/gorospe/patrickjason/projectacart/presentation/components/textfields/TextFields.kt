package ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.textfields

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String? = null,
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String? = null,
) {
    Column(modifier = modifier) {
        // Static label text above the text field
        Text(
            text = label,
            color = AppTheme.colorScheme.onBackground,
//            style = MaterialTheme.typography.bodyMedium, // Customize as per your theme
            style = AppTheme.typography.body1,
            modifier = Modifier.padding(bottom = AppTheme.sizes.small)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            shape = AppTheme.shapes.button,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = AppTheme.colorScheme.background,
                focusedTextColor = AppTheme.colorScheme.onBackground,
                focusedBorderColor = AppTheme.colorScheme.onBackground,
                unfocusedBorderColor = AppTheme.colorScheme.onBackground
            ),
            singleLine = true,
            leadingIcon = {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = leadingIconContentDescription,
                        tint = AppTheme.colorScheme.onBackground,
                        modifier = Modifier.size(AppTheme.sizes.large)
                    )
                }
            },
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = trailingIconContentDescription,
                        tint = AppTheme.colorScheme.onBackground,
                        modifier = Modifier.size(AppTheme.sizes.large)
                    )
                }
            }
        )
    }
}

@Composable
fun StyledTextFieldLight(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String? = null,
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String? = null,
) {
    MaterialTheme(colorScheme = lightColorScheme()) {
        StyledTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            label = label,
            enabled = enabled,
            leadingIcon = leadingIcon,
            leadingIconContentDescription = leadingIconContentDescription,
            trailingIcon = trailingIcon,
            trailingIconContentDescription = trailingIconContentDescription
        )
    }
}

@Composable
fun StyledTextFieldDark(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    leadingIconContentDescription: String? = null,
    trailingIcon: ImageVector? = null,
    trailingIconContentDescription: String? = null,
) {
    AppTheme(isDarkTheme = true) {
        StyledTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            label = label,
            enabled = enabled,
            leadingIcon = leadingIcon,
            leadingIconContentDescription = leadingIconContentDescription,
            trailingIcon = trailingIcon,
            trailingIconContentDescription = trailingIconContentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StyledTextFieldPreview() {
    AppTheme {
        // State to hold the text field value
        var textValue by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(AppTheme.sizes.medium),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.sizes.normal)
        ) {
            StyledTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = "Action Input with Icons",
                leadingIcon = Icons.Default.Person,
                leadingIconContentDescription = "Leading Done Icon",
                trailingIcon = Icons.Default.Done,
                trailingIconContentDescription = "Trailing Done Icon"
            )
            StyledTextFieldLight(
                value = textValue,
                onValueChange = { textValue = it },
                label = "Light Theme",
                leadingIcon = Icons.Default.Person,
                leadingIconContentDescription = "Leading Done Icon",
                trailingIcon = Icons.Default.Done,
                trailingIconContentDescription = "Trailing Done Icon"
            )
            StyledTextFieldDark(
                value = textValue,
                onValueChange = { textValue = it },
                label = "Dark Theme",
                leadingIcon = Icons.Default.Person,
                leadingIconContentDescription = "Leading Done Icon",
                trailingIcon = Icons.Default.Done,
                trailingIconContentDescription = "Trailing Done Icon"
            )
        }
    }
}

