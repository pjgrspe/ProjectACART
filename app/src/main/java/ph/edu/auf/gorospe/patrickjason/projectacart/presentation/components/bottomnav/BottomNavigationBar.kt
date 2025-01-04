package ph.edu.auf.gorospe.patrickjason.projectacart.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ph.edu.auf.gorospe.patrickjason.projectacart.presentation.components.bottomnav.BottomNavigationItem
import ph.edu.auf.gorospe.patrickjason.projectacart.ui.theme.AppTheme

//ITEMS
val items = listOf(
    BottomNavigationItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        badgeCount = null
    ),
    BottomNavigationItem(
        title = "Saved",
        selectedIcon = Icons.Filled.Star,
        unselectedIcon = Icons.Outlined.Star,
        badgeCount = null
    ),
    BottomNavigationItem(
        title = "Alerts",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        badgeCount = 2
    ),
    BottomNavigationItem(
        title = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        badgeCount = null
    )
)

//BOTTOM NAVIGATION BAR
@Preview(showBackground = true)
@Composable
fun BottomNavigationBar() {
    //Tracks the currently selected item in the bottom navigation bar
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    //Main navigation bar with rounded corners and elevated background
    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = AppTheme.sizes.medium, topEnd = AppTheme.sizes.medium))
            .height(90.dp),
        containerColor = AppTheme.colorScheme.background,
        tonalElevation = AppTheme.sizes.small
    ) {
        items.forEachIndexed { index, item ->
            //check if item is selected
            val isSelected = selectedItemIndex == index

            //icon color based on selection state
            val iconColor = if (isSelected) AppTheme.colorScheme.primary else AppTheme.colorScheme.onBackground

            //bounce animation when selected
            val scale = animateFloatAsState(if (isSelected) 1.2f else 1f)

            //column for each item
            Column(
                modifier = Modifier
                    .weight(1f)  //Distribute items equally in the navigation bar
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            selectedItemIndex = index  //Update selected item on tap
                        })
                    }
                    .padding(vertical = AppTheme.sizes.small),  //Add vertical padding to each item
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //Badge box to display an optional badge on top of the icon
                BadgedBox(badge = {
                    item.badgeCount?.let {
                        Badge { Text(text = it.toString()) }
                    }
                }) {
                    //Icon with a scaling effect for the bounce animation
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.title,
                        tint = iconColor,
                        modifier = Modifier
                            .size(AppTheme.sizes.large)
                            .graphicsLayer(scaleX = scale.value, scaleY = scale.value)  // Apply bounce scale
                    )
                }

                Spacer(modifier = Modifier.height(AppTheme.sizes.tiny))  // Space between icon and text

                //Display the title of the item, with bold font if selected
                Text(
                    text = item.title,
                    color = iconColor,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

